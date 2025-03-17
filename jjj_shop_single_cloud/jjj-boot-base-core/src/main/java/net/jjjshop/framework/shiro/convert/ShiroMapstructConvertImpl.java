package net.jjjshop.framework.shiro.convert;

import net.jjjshop.framework.shiro.jwt.JwtToken;
import net.jjjshop.framework.shiro.vo.JwtTokenRedisVo;

public class ShiroMapstructConvertImpl implements ShiroMapstructConvert {

    @Override
    public JwtTokenRedisVo jwtTokenToJwtTokenRedisVo(JwtToken jwtToken) {
        if (jwtToken == null) {
            return null;
        }

        JwtTokenRedisVo jwtTokenRedisVo = new JwtTokenRedisVo();

        jwtTokenRedisVo.setHost(jwtToken.getHost());
        jwtTokenRedisVo.setUsername(jwtToken.getUsername());
        jwtTokenRedisVo.setSalt(jwtToken.getSalt());
        jwtTokenRedisVo.setToken(jwtToken.getToken());
        jwtTokenRedisVo.setCreateDate(jwtToken.getCreateDate());
        jwtTokenRedisVo.setExpireSecond(jwtToken.getExpireSecond());
        jwtTokenRedisVo.setExpireDate(jwtToken.getExpireDate());

        return jwtTokenRedisVo;
    }
}
