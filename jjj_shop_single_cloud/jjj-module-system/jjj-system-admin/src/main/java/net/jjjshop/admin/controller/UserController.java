

package net.jjjshop.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.admin.service.AdminUserService;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "admin-用户")
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/renew", method = RequestMethod.POST)
    @OperationLog(name = "renew")
    @Operation(summary = "renew")
    public ApiResult<String> renew(String pass) {
        if(adminUserService.renew(pass)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }
}
