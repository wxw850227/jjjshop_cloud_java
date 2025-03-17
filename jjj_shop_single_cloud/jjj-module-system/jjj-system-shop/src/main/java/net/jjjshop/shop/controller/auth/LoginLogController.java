package net.jjjshop.shop.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.shop.LoginLogPageParam;
import net.jjjshop.shop.service.shop.ShopLoginLogService;
import net.jjjshop.shop.vo.shop.LoginLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-登录日志")
@RestController
@RequestMapping("/shop/auth/loginlog")
public class LoginLogController {

    @Autowired
    private ShopLoginLogService shopLoginLogService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/auth/loginlog/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<LoginLogVo>> index(@RequestBody LoginLogPageParam loginLogPageParam) throws Exception {
        return ApiResult.ok(shopLoginLogService.getList(loginLogPageParam));
    }
}
