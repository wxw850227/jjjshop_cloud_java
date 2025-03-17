package net.jjjshop.shop.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.user.UserGradeLogPageParam;
import net.jjjshop.shop.service.user.UserGradeLogService;
import net.jjjshop.shop.vo.user.UserGradeLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-会员升级")
@RestController
@RequestMapping("/shop/user/grade")
public class UserGradeLogController {

    @Autowired
    private UserGradeLogService userGradeLogService;

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    @RequiresPermissions("/user/grade/log")
    @OperationLog(name = "log")
    @Operation(summary = "log")
    public ApiResult<Paging<UserGradeLogVo>> log(@Validated @RequestBody UserGradeLogPageParam userGradeLogPageParam) throws Exception {
        return ApiResult.ok(userGradeLogService.getList(userGradeLogPageParam));
    }
}
