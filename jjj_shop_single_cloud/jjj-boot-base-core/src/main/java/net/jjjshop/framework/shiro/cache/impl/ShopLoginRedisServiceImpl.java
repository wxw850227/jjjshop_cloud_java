

package net.jjjshop.framework.shiro.cache.impl;

import net.jjjshop.config.constant.CommonRedisKey;
import net.jjjshop.config.properties.JwtProperties;
import net.jjjshop.framework.common.bean.ClientInfo;
import net.jjjshop.framework.shiro.cache.ShopLoginRedisService;
import net.jjjshop.framework.shiro.convert.ShiroMapstructConvert;
import net.jjjshop.framework.shiro.jwt.JwtToken;
import net.jjjshop.framework.shiro.vo.JwtTokenRedisVo;
import net.jjjshop.framework.shiro.vo.LoginShopUserRedisVo;
import net.jjjshop.framework.shiro.vo.LoginShopUserVo;
import net.jjjshop.framework.util.ClientInfoUtil;
import net.jjjshop.framework.util.HttpServletRequestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * 登录信息Redis缓存服务类
 **/
@Service
public class ShopLoginRedisServiceImpl implements ShopLoginRedisService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * key-value: 有过期时间-->token过期时间
     * 1. tokenMd5:jwtTokenRedisVo
     * 2. username:loginSysUserRedisVo
     * 3. username:salt
     * hash: 没有过期时间，统计在线的用户信息
     * username:num
     */
    @Override
    public void cacheLoginInfo(JwtToken jwtToken, LoginShopUserVo loginShopUserVo) {
        if (jwtToken == null) {
            throw new IllegalArgumentException("jwtToken不能为空");
        }
        if (loginShopUserVo == null) {
            throw new IllegalArgumentException("loginShopUserVo不能为空");
        }
        // token
        String token = jwtToken.getToken();
        // 盐值
        String salt = jwtToken.getSalt();
        // 登录用户名称
        String username = loginShopUserVo.getUserName();
        // token md5值
        String tokenMd5 = DigestUtils.md5Hex(token);

        // Redis缓存JWT Token信息
        JwtTokenRedisVo jwtTokenRedisVo = ShiroMapstructConvert.INSTANCE.jwtTokenToJwtTokenRedisVo(jwtToken);

        // 用户客户端信息
        ClientInfo clientInfo = ClientInfoUtil.get(HttpServletRequestUtil.getRequest());

        // Redis缓存登录用户信息
        // 将loginShopUserVo对象复制到loginShopUserRedisVo
        LoginShopUserRedisVo loginShopUserRedisVo = new LoginShopUserRedisVo();
        BeanUtils.copyProperties(loginShopUserVo, loginShopUserRedisVo);
        loginShopUserRedisVo.setSalt(salt);
        loginShopUserRedisVo.setClientInfo(clientInfo);

        // Redis过期时间与JwtToken过期时间一致
        Duration expireDuration = Duration.ofSeconds(jwtToken.getExpireSecond());

        // 判断是否启用单个用户登录，如果是，这每个用户只有一个有效token
        boolean singleLogin = jwtProperties.isSingleLogin();
        if (singleLogin) {
            deleteUserAllCache(username);
        }

        // 1. tokenMd5:jwtTokenRedisVo
        String loginTokenRedisKey = String.format(CommonRedisKey.SHOP_LOGIN_TOKEN, tokenMd5);
        redisTemplate.opsForValue().set(loginTokenRedisKey, jwtTokenRedisVo, expireDuration);
        // 2. username:loginSysUserRedisVo
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.SHOP_LOGIN_USER, username), loginShopUserRedisVo, expireDuration);
        // 3. salt hash,方便获取盐值鉴权
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.SHOP_LOGIN_SALT, username), salt, expireDuration);
        // 4. login user token
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.SHOP_LOGIN_USER_TOKEN, username, tokenMd5), loginTokenRedisKey, expireDuration);
    }

    @Override
    public void refreshLoginInfo(String oldToken, String username, JwtToken newJwtToken) {
        // 获取缓存的登录用户信息
        LoginShopUserRedisVo loginShopUserRedisVo = getLoginShopUserRedisVo(username);
        // 删除之前的token信息
        deleteLoginInfo(oldToken, username);
        // 缓存登录信息
        cacheLoginInfo(newJwtToken, loginShopUserRedisVo);
    }

    @Override
    public LoginShopUserRedisVo getLoginShopUserRedisVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        return (LoginShopUserRedisVo) redisTemplate.opsForValue().get(String.format(CommonRedisKey.SHOP_LOGIN_USER, username));
    }

    @Override
    public LoginShopUserVo getLoginShopUserVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        LoginShopUserRedisVo userRedisVo = getLoginShopUserRedisVo(username);
        return userRedisVo;
    }

    @Override
    public String getSalt(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        String salt = (String) redisTemplate.opsForValue().get(String.format(CommonRedisKey.SHOP_LOGIN_SALT, username));
        return salt;
    }

    @Override
    public void deleteLoginInfo(String token, String username) {
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

    @Override
    public boolean exists(String token) {
        if (token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
        String tokenMd5 = DigestUtils.md5Hex(token);
        Object object = redisTemplate.opsForValue().get(String.format(CommonRedisKey.SHOP_LOGIN_TOKEN, tokenMd5));
        return object != null;
    }

    @Override
    public void deleteUserAllCache(String username) {
        Set<String> userTokenMd5Set = redisTemplate.keys(String.format(CommonRedisKey.SHOP_LOGIN_USER_ALL_TOKEN, username));
        if (CollectionUtils.isEmpty(userTokenMd5Set)) {
            return;
        }

        // 1. 删除登录用户的所有token信息
        List<String> tokenMd5List = redisTemplate.opsForValue().multiGet(userTokenMd5Set);
        redisTemplate.delete(tokenMd5List);
        // 2. 删除登录用户的所有user:token信息
        redisTemplate.delete(userTokenMd5Set);
        // 3. 删除登录用户信息
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_USER, username));
        // 4. 删除登录用户盐值信息
        redisTemplate.delete(String.format(CommonRedisKey.SHOP_LOGIN_SALT, username));
    }


}
