

package net.jjjshop.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.admin.AdminUser;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.mapper.admin.AdminUserMapper;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.common.settings.vo.SysConfigVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.config.constant.CommonRedisKey;
import net.jjjshop.config.properties.JwtProperties;
import net.jjjshop.config.properties.SpringBootJjjProperties;
import net.jjjshop.framework.common.exception.BusinessException;
import net.jjjshop.framework.common.service.impl.BaseServiceImpl;
import net.jjjshop.framework.shiro.cache.AdminLoginRedisService;
import net.jjjshop.framework.shiro.jwt.JwtToken;
import net.jjjshop.framework.shiro.util.JwtTokenUtil;
import net.jjjshop.framework.shiro.util.JwtUtil;
import net.jjjshop.framework.shiro.util.SaltUtil;
import net.jjjshop.framework.shiro.vo.LoginAdminUserVo;
import net.jjjshop.framework.util.PasswordUtil;
import net.jjjshop.admin.service.AdminUserService;
import net.jjjshop.admin.vo.LoginAdminUserTokenVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * 系统用户 服务实现类
 */
@Slf4j
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

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
    private AdminLoginRedisService adminLoginRedisService;

    @Lazy
    @Autowired
    private SettingUtils settingUtils;

    @Override
    public LoginAdminUserTokenVo login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        AdminUser adminUser = adminUserMapper.selectOne(new LambdaQueryWrapper<AdminUser>()
                .eq(AdminUser::getUserName, username));
        if (adminUser == null) {
            throw new BusinessException("用户名或密码错误");
        }
        String encryptPassword = PasswordUtil.encrypt(password, adminUser.getSalt());
        if (!encryptPassword.equals(adminUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        JSONObject jsonData = settingUtils.getSetting(SettingEnum.SYS_CONFIG.getKey(), 0L);
        if (jsonData != null) {
            SysConfigVo sysConfigVo = JSONObject.toJavaObject(jsonData, SysConfigVo.class);
            //登录验证码开关,true开,false关
            if (sysConfigVo != null && sysConfigVo.getAdminLoginCode()) {
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
        LoginAdminUserVo loginAdminUserVo = new LoginAdminUserVo();
        BeanUtils.copyProperties(adminUser, loginAdminUserVo);
        // 获取数据库中保存的盐值
        String newSalt = SaltUtil.getSalt(adminUser.getSalt(), jwtProperties);

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
        adminLoginRedisService.cacheLoginInfo(jwtToken, loginAdminUserVo);
        log.debug("登录成功,username:{}", username);

        // 缓存登录信息到redis
        String tokenSha256 = DigestUtils.sha256Hex(token);
        redisTemplate.opsForValue().set(tokenSha256, loginAdminUserVo, 1, TimeUnit.DAYS);

        // 返回token和登录用户信息对象
        LoginAdminUserTokenVo loginAdminUserTokenVo = new LoginAdminUserTokenVo();
        loginAdminUserTokenVo.setToken(token);
        loginAdminUserTokenVo.setLoginAdminUserVo(loginAdminUserVo);
        return loginAdminUserTokenVo;
    }

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    @Override
    public Boolean renew(String password) {
        AdminUser adminUser = adminUserMapper.selectOne(new LambdaQueryWrapper<AdminUser>());

        String encryptPassword = PasswordUtil.encrypt(password, adminUser.getSalt());
        AdminUser updateBean = new AdminUser();
        updateBean.setAdminUserId(adminUser.getAdminUserId());
        updateBean.setPassword(encryptPassword);
        return this.updateById(updateBean);
    }

    @Override
    public void logout(HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 获取token
        String token = JwtTokenUtil.getToken(request, "admin");
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
        redisTemplate.delete(String.format(CommonRedisKey.ADMIN_LOGIN_TOKEN, tokenMd5));
        // 2. delete username
        redisTemplate.delete(String.format(CommonRedisKey.ADMIN_LOGIN_USER, username));
        // 3. delete salt
        redisTemplate.delete(String.format(CommonRedisKey.ADMIN_LOGIN_SALT, username));
        // 4. delete user token
        redisTemplate.delete(String.format(CommonRedisKey.ADMIN_LOGIN_USER_TOKEN, username, tokenMd5));
    }
}
