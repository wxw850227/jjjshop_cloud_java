

package net.jjjshop.front.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.framework.shiro.util.JwtTokenUtil;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.param.AppWxParam;
import net.jjjshop.front.service.user.UserService;
import net.jjjshop.front.vo.user.LoginUserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "front-微信小程序")
@RestController
@RequestMapping("/front/user/userWx")
public class UserWxController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @OperationLog(name = "login")
    @Operation(summary = "login")
    public ApiResult<LoginUserTokenVo> login(@RequestBody AppWxParam appWxParam){
        log.debug("login...");
        LoginUserTokenVo user = userService.loginWx(appWxParam);
        // 设置token响应头
        response.setHeader(JwtTokenUtil.getTokenName(""), user.getToken());
        return ApiResult.ok(user, "登录成功");
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.POST)
    @OperationLog(name = "getSession")
    @Operation(summary = "getSession")
    public ApiResult<String> getSessionKey(String code){
        String sessionKey = userService.getSessionKey(code);
        return ApiResult.ok(sessionKey, "");
    }


    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @OperationLog(name = "bindMobile")
    @Operation(summary = "bindMobile")
    public ApiResult<String> bindMobile(String sessionKey, String encryptedData, String iv){
        User user = this.getUser(true);
        String mobile = userService.bindMobileByWx(user.getUserId(), sessionKey, encryptedData, iv);
        if(!"".equals(mobile)){
            return ApiResult.ok(mobile, "");
        }
        return ApiResult.fail("绑定失败，请重试");
    }
}
