

package net.jjjshop.admin.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.framework.log.annotation.OperationLogIgnore;
import net.jjjshop.framework.shiro.util.JwtTokenUtil;
import net.jjjshop.admin.service.AdminUserService;
import net.jjjshop.admin.vo.LoginAdminUserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
@Tag(name = "admin-登录")
@RestController
@RequestMapping("/admin/passport")
public class PassportController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @OperationLog(name = "login")
    @Operation(summary = "login")
    public ApiResult<LoginAdminUserTokenVo> login(@RequestBody LoginParam param, HttpServletResponse response) throws IOException {
        log.debug("username..." + param.getUsername());
        log.debug("password..." + param.getPassword());
        LoginAdminUserTokenVo user = adminUserService.login(param);
        // 设置token响应头
        response.setHeader(JwtTokenUtil.getTokenName("admin"), user.getToken());
        return ApiResult.ok(user, "登录成功");
    }


    @PostMapping("/logout")
    @OperationLogIgnore
    public ApiResult<String> logout(HttpServletRequest request) throws Exception {
        adminUserService.logout(request);
        return ApiResult.ok("退出成功");
    }
}
