package net.jjjshop.shop.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.order.OrderExtractParam;
import net.jjjshop.shop.param.order.OrderPageParam;
import net.jjjshop.shop.param.order.OrderParam;
import net.jjjshop.shop.service.order.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Tag(name = "shop-订单管理")
@RestController
@RequestMapping("/shop/order/operate")
public class OrderOperateController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/confirmCancel", method = RequestMethod.POST)
    @RequiresPermissions("/order/operate/confirmCancel")
    @OperationLog(name = "confirmCancel")
    @Operation(summary = "confirmCancel")
    public ApiResult<String> confirmCancel(@Validated @RequestBody OrderParam orderParam) throws Exception{
        if(orderService.confirmCancel(orderParam)){
            return ApiResult.ok(null, "审核成功");
        }else{
            return ApiResult.fail("审核失败");
        }
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @RequiresPermissions("/order/operate/export")
    @OperationLog(name = "export")
    @Operation(summary = "export")
    public ApiResult<String> export(OrderPageParam orderPageParam, HttpServletResponse httpServletResponse) throws Exception{
        orderService.exportList(orderPageParam, httpServletResponse);
            return ApiResult.ok(null, "订单导出成功");
    }

    @RequestMapping(value = "/extract", method = RequestMethod.POST)
    @RequiresPermissions("/order/operate/extract")
    @OperationLog(name = "extract")
    @Operation(summary = "extract")
    public ApiResult<String> extract(@Validated @RequestBody OrderExtractParam orderExtractParam) throws Exception{
        if(orderService.verificationOrder(orderExtractParam)){
            return ApiResult.ok(null, "订单核销成功");
        }else {
            return ApiResult.fail("订单核销失败");
        }
    }

}
