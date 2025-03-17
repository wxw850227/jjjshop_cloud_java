

package net.jjjshop.shop.service.shop.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.app.App;
import net.jjjshop.common.entity.shop.ShopAccess;
import net.jjjshop.common.entity.shop.ShopUser;
import net.jjjshop.common.entity.shop.ShopUserRole;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.mapper.shop.ShopUserRoleMapper;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.common.service.app.AppService;
import net.jjjshop.common.service.shop.ShopAccessService;
import net.jjjshop.common.settings.vo.StoreVo;
import net.jjjshop.common.settings.vo.SysConfigVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.config.constant.CommonConstant;
import net.jjjshop.config.constant.CommonRedisKey;
import net.jjjshop.config.properties.JwtProperties;
import net.jjjshop.config.properties.SpringBootJjjProperties;
import net.jjjshop.framework.common.exception.BusinessException;
import net.jjjshop.framework.common.service.impl.BaseServiceImpl;
import net.jjjshop.framework.core.pagination.PageInfo;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.shiro.cache.ShopLoginRedisService;
import net.jjjshop.framework.shiro.jwt.JwtToken;
import net.jjjshop.framework.shiro.util.JwtTokenUtil;
import net.jjjshop.framework.shiro.util.JwtUtil;
import net.jjjshop.framework.shiro.util.SaltUtil;
import net.jjjshop.framework.shiro.vo.LoginShopAccessVo;
import net.jjjshop.framework.shiro.vo.LoginShopUserTokenVo;
import net.jjjshop.framework.shiro.vo.LoginShopUserVo;
import net.jjjshop.framework.util.PasswordUtil;
import net.jjjshop.framework.util.ShopLoginUtil;
import net.jjjshop.shop.mapper.shop.ShopUserMapper;
import net.jjjshop.shop.param.shopUser.ShopUserPageParam;
import net.jjjshop.shop.param.shopUser.ShopUserParam;
import net.jjjshop.shop.service.shop.ShopUserRoleService;
import net.jjjshop.shop.service.shop.ShopUserService;
import net.jjjshop.shop.vo.shopUser.ShopUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 系统用户 服务实现类
 */
@Slf4j
@Service
public class ShopUserServiceImpl extends BaseServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;
    @Autowired
    private ShopUserRoleMapper shopUserRoleMapper;
    @Lazy
    @Autowired
    private SpringBootJjjProperties springBootJjjProperties;
    @Lazy
    @Autowired
    private JwtProperties jwtProperties;
    @Lazy
    @Autowired
    private RedisTemplate redisTemplate;
    @Lazy
    @Autowired
    private ShopLoginRedisService shopLoginRedisService;
    @Autowired
    private ShopAccessService shopAccessService;
    @Autowired
    private ShopUserRoleService shopUserRoleService;
    @Autowired
    private AppService appService;
    @Autowired
    private SettingUtils settingUtils;

    @Override
    public LoginShopUserTokenVo login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        ShopUser shopUser = shopUserMapper.selectByName(username);
        if (shopUser == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        String encryptPassword = PasswordUtil.encrypt(password, shopUser.getSalt());
        if (!encryptPassword.equals(shopUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        // 检查应用
        App app = appService.getById(shopUser.getTenantId());
        if (app == null) {
            throw new AuthenticationException("登录失败, 未找到应用信息");
        }
        if (app.getIsRecycle()) {
            throw new AuthenticationException("登录失败, 当前应用已禁用");
        }
        if (app.getIsDelete()) {
            throw new AuthenticationException("登录失败, 当前应用已删除");
        }
        if (app.getExpireTime() != null && app.getExpireTime().before(new Date())) {
            throw new AuthenticationException("登录失败, 当前应用已过期，请联系平台续费");
        }
        JSONObject jsonData = settingUtils.getSetting(SettingEnum.SYS_CONFIG.getKey(), 0L);
        if (jsonData != null) {
            SysConfigVo sysConfigVo = JSONObject.toJavaObject(jsonData, SysConfigVo.class);
            //登录验证码开关,true开,false关
            if (sysConfigVo != null && sysConfigVo.getLoginCode()) {
                if (StringUtils.isBlank(param.getCodeKey())) {
                    throw new BusinessException("验证码Key值不能为空");
                }
                if (StringUtils.isBlank(param.getCode())) {
                    throw new BusinessException("验证码不能为空");
                }
                //获取redis保存的验证码
                String code = (String) redisTemplate.opsForValue().get(param.getCodeKey());
                if (StringUtils.isBlank(code)) {
                    throw new BusinessException("验证码已过期,请刷新后再试");
                }
                if (!code.equals(param.getCode())) {
                    throw new BusinessException("验证码错误");
                }
            }
        }
        // 将系统用户对象转换成登录用户对象
        LoginShopUserVo loginShopUserVo = new LoginShopUserVo();
        BeanUtils.copyProperties(shopUser, loginShopUserVo);
        // 获取用户权限集合
        // 1.如果是超管，则有所有权限
        List<ShopAccess> accessList = null;
        if (shopUser.getIsSuper()) {
            accessList = shopAccessService.list(new LambdaQueryWrapper<ShopAccess>()
                    .eq(ShopAccess::getIsShow, true)
                    .orderByAsc(ShopAccess::getSort).orderByAsc(ShopAccess::getCreateTime)
                    );
        } else {
            accessList = shopUserMapper.getAccessByUserId(shopUser.getShopUserId());
            if (CollectionUtils.isEmpty(accessList)) {
                throw new AuthenticationException("权限列表不能为空");
            }
        }
        loginShopUserVo.setPermissions(this.transPermissions(accessList));
        loginShopUserVo.setMenus(this.transMenus(accessList));
        // 获取数据库中保存的盐值
        String newSalt = SaltUtil.getSalt(shopUser.getSalt(), jwtProperties);

        // 生成token字符串并返回
        Long expireSecond = jwtProperties.getExpireSecond();
        String token = JwtUtil.generateToken(username, newSalt, Duration.ofSeconds(expireSecond));
        log.debug("token:{}", token);

        // 创建AuthenticationToken
        JwtToken jwtToken = JwtToken.build(token, username, newSalt, expireSecond);

        boolean enableShiro = springBootJjjProperties.getShiro().isEnable();
        if (enableShiro) {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 执行认证登录
            subject.login(jwtToken);
        } else {
            log.warn("未启用Shiro");
        }

        // 缓存登录信息到Redis
        shopLoginRedisService.cacheLoginInfo(jwtToken, loginShopUserVo);
        log.debug("登录成功,username:{}", username);

        // 缓存登录信息到redis
        String tokenSha256 = DigestUtils.sha256Hex(token);
        redisTemplate.opsForValue().set(tokenSha256, loginShopUserVo, 1, TimeUnit.DAYS);

        // 返回token和登录用户信息对象
        LoginShopUserTokenVo loginShopUserTokenVo = new LoginShopUserTokenVo();
        loginShopUserTokenVo.setToken(token);
        loginShopUserTokenVo.setLoginShopUserVo(loginShopUserVo);
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORE.getKey(), app.getTenantId().longValue());
        StoreVo storeVo = JSONObject.parseObject(vo.toJSONString(), StoreVo.class);
        loginShopUserTokenVo.setLogoUrl(storeVo.getLogoUrl());
        return loginShopUserTokenVo;
    }

    /**
     * 后端权限集合
     *
     * @param accessList
     * @return
     */
    private Set<String> transPermissions(List<ShopAccess> accessList) {
        Set<String> set = new HashSet<String>();
        accessList.forEach(item -> {
            set.add(item.getPath());
        });
        return set;
    }

    /**
     * 前端权限菜单集合
     *
     * @param accessList
     * @return
     */
    private List<LoginShopAccessVo> transMenus(List<ShopAccess> accessList) {
        // 转成vo
        List<LoginShopAccessVo> voList = accessList.stream().map(e -> {
            LoginShopAccessVo shopAccessVo = new LoginShopAccessVo();
            BeanUtils.copyProperties(e, shopAccessVo);
            return shopAccessVo;
        }).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(LoginShopAccessVo::getAccessId))), ArrayList::new));
        voList.sort(Comparator.comparing(LoginShopAccessVo::getSort));
        // 遍历成树形结构
        List<LoginShopAccessVo> collect = voList.stream()
                // 2. 找出所有顶级（规定 0 为顶级）
                .filter(o -> StrUtil.equals("0", String.valueOf(o.getParentId())))
                // 3.给当前父级的 childList 设置子
                .peek(o -> o.setChildren(getChildList(o, voList)))
                // 4.收集
                .collect(Collectors.toList());
        collect.sort(Comparator.comparing(LoginShopAccessVo::getSort));
        return collect;
    }

    // 根据当前父类 找出子类， 并通过递归找出子类的子类
    private List<LoginShopAccessVo> getChildList(ShopAccess bean, List<LoginShopAccessVo> voList) {
        return voList.stream()
                //筛选出父节点id == parentId 的所有对象 => list
                .filter(o -> StrUtil.equals(String.valueOf(bean.getAccessId()), String.valueOf(o.getParentId())))
                .peek(o -> o.setChildren(getChildList(o, voList)))
                .collect(Collectors.toList());
    }

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    @Override
    public Boolean renew(String oldpass, String password, String confirmPass) {
        if (StringUtils.isBlank(oldpass) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPass)) {
            throw new BusinessException("密码不能为空");
        }
        // 当前登录用户
        ShopUser shopUser = this.getById(ShopLoginUtil.getUserId());
        if (!password.equals(confirmPass)) {
            throw new BusinessException("两次密码不相同");
        }
        String encryptOldPassword = PasswordUtil.encrypt(oldpass, shopUser.getSalt());
        if (!encryptOldPassword.equals(shopUser.getPassword())) {
            throw new BusinessException("原密码不正确");
        }
        String encryptPassword = PasswordUtil.encrypt(password, shopUser.getSalt());
        ShopUser updateBean = new ShopUser();
        updateBean.setShopUserId(shopUser.getShopUserId());
        updateBean.setPassword(encryptPassword);
        return this.updateById(updateBean);
    }

    @Override
    public void logout(HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 获取token
        String token = JwtTokenUtil.getToken(request, "shop");
        String username = JwtUtil.getUsername(token);
        // 删除Redis缓存信息
        deleteLoginInfo(token, username);
        log.info("登出成功,username:{},token:{}", username, token);
    }

    private void deleteLoginInfo(String token, String username) {
        if (token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
        if (username == null) {
            throw new IllegalArgumentException("username不能为空");
        }
        String tokenMd5 = DigestUtils.md5Hex(token);
        // 1. delete tokenMd5
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_TOKEN, tokenMd5));
        // 2. delete username
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_USER, username));
        // 3. delete salt
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_SALT, username));
        // 4. delete user token
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_USER_TOKEN, username, tokenMd5));
    }

    /**
     * 列表
     *
     * @param params
     * @throws Exception
     */
    @Override
    public Paging<ShopUserVo> getList(ShopUserPageParam params) {
        Page<ShopUser> page = new PageInfo<>(params);
        IPage<ShopUser> iPage = this.page(page, new LambdaQueryWrapper<ShopUser>().eq(ShopUser::getIsDelete, false));
        // 最终返回分页对象
        IPage<ShopUserVo> resultPage = iPage.convert(item -> {
            ShopUserVo vo = new ShopUserVo();
            BeanUtil.copyProperties(item, vo);
            // 角色名称
            if (!vo.getIsSuper()) {
                vo.setRoleList(shopUserRoleMapper.getRoleListByUser(vo.getShopUserId()));
            }
            return vo;
        });
        return new Paging(resultPage);
    }

    /**
     * 新增用户
     *
     * @param shopUserParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(ShopUserParam shopUserParam) {
        if (StrUtil.isBlank(shopUserParam.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        if (StrUtil.isBlank(shopUserParam.getConfirmPassword())) {
            throw new BusinessException("确认密码不能为空");
        }
        if (!shopUserParam.getPassword().equals(shopUserParam.getConfirmPassword())) {
            throw new BusinessException("两次密码不一致");
        }
        shopUserParam.setUserName(shopUserParam.getUserName().trim());
        shopUserParam.setPassword(shopUserParam.getPassword().trim());
        shopUserParam.setRealName(shopUserParam.getRealName().trim());
        //验证账号唯一性
        long count = shopUserMapper.selectCountByName(shopUserParam.getUserName());
        if (count > 0) {
            throw new BusinessException("该用户名已存在，请更改后再试");
        }
        ShopUser user = new ShopUser();
        user.setUserName(shopUserParam.getUserName());
        user.setRealName(shopUserParam.getRealName());
        // 盐值
        String salt = SaltUtil.generateSalt();
        // 密码加密
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encrypt(shopUserParam.getPassword(), salt));
        this.save(user);
        // 保存角色
        this.saveRole(shopUserParam, user.getShopUserId());
        return true;
    }

    /**
     * 编辑用户
     *
     * @param shopUserParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(ShopUserParam shopUserParam) {
        shopUserParam.setUserName(shopUserParam.getUserName().trim());
        shopUserParam.setPassword(shopUserParam.getPassword() == null ? null : shopUserParam.getPassword().trim());
        shopUserParam.setRealName(shopUserParam.getRealName().trim());
        ShopUser oldUser = this.getById(shopUserParam.getShopUserId());
        //如果修改了账号，则验证账号唯一性
        if (!oldUser.getUserName().equals(shopUserParam.getUserName())) {
            //验证账号唯一性
            long count = shopUserMapper.selectCountByName(shopUserParam.getUserName());
            if (count > 0) {
                throw new BusinessException("该用户名已存在，请更改后再试");
            }
        }
        ShopUser user = new ShopUser();
        user.setShopUserId(shopUserParam.getShopUserId());
        user.setUserName(shopUserParam.getUserName());
        user.setRealName(shopUserParam.getRealName());
        if (StringUtils.isNotBlank(shopUserParam.getPassword())) {
            if (StrUtil.isBlank(shopUserParam.getConfirmPassword())) {
                throw new BusinessException("确认密码不能为空");
            }
            if (!shopUserParam.getPassword().equals(shopUserParam.getConfirmPassword())) {
                throw new BusinessException("两次密码不一致");
            }
            // 盐值
            String salt = SaltUtil.generateSalt();
            // 密码加密
            user.setSalt(salt);
            user.setPassword(PasswordUtil.encrypt(shopUserParam.getPassword(), salt));
        }
        this.updateById(user);
        // 先删除角色
        shopUserRoleService.remove(new LambdaQueryWrapper<ShopUserRole>()
                .eq(ShopUserRole::getShopUserId, shopUserParam.getShopUserId()));
        // 保存角色
        this.saveRole(shopUserParam, shopUserParam.getShopUserId());
        return true;
    }

    private void saveRole(ShopUserParam shopUserParam, Integer shopUserId) {
        List<ShopUserRole> roleList = new ArrayList<>();
        shopUserParam.getRoleId().forEach(item -> {
            ShopUserRole role = new ShopUserRole();
            role.setRoleId(item);
            role.setShopUserId(shopUserId);
            roleList.add(role);
        });
        shopUserRoleService.saveBatch(roleList);
    }

    /**
     * 删除用户
     *
     * @param shopUserId
     * @return
     */
    @Override
    public Boolean setDelete(Integer shopUserId) {
        return this.update(new LambdaUpdateWrapper<ShopUser>()
                .eq(ShopUser::getShopUserId, shopUserId).set(ShopUser::getIsDelete, true));
    }
}
