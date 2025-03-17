

package net.jjjshop.front.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.order.Order;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.common.enums.OrderTypeEnum;
import net.jjjshop.common.util.poster.ExtractUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.param.order.OrderPageParam;
import net.jjjshop.front.param.order.OrderPayParam;
import net.jjjshop.front.service.app.AppService;
import net.jjjshop.front.service.order.OrderService;
import net.jjjshop.front.service.user.UserOrderService;
import net.jjjshop.front.vo.order.OrderDetailVo;
import net.jjjshop.front.vo.order.OrderListVo;
import net.jjjshop.front.vo.settings.ExpressDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "front-订单")
@RestController
@RequestMapping("/front/user/order")
public class UserOrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppService appService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private ExtractUtils extractUtils;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<Paging<OrderListVo>> lists(@RequestBody OrderPageParam orderPageParam){
        orderPageParam.setUserId(this.getUser(true).getUserId());
        return ApiResult.ok(orderService.getList(orderPageParam));
    }

    @RequestMapping(value = "/toPay", method = RequestMethod.GET)
    @OperationLog(name = "toPay")
    @Operation(summary = "toPay")
    public ApiResult<Map<String, Object>> toPay(Integer orderId, String paySource){
        User user = this.getUser(true);
        Map<String, Object> result = new HashMap<>();
        Order order = orderService.getUserOrderDetail(orderId, user.getUserId());
        result.put("payPrice", order.getPayPrice());
        result.put("balance", user.getBalance());
        // 开启的支付类型
        result.put("payTypes", appService.getPayType(paySource));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @OperationLog(name = "pay")
    @Operation(summary = "pay")
    public ApiResult<Map<String, Object>> pay(@RequestBody OrderPayParam orderPayParam){
        User user = this.getUser(true);
        Map<String, Object> result = orderService.orderPay(orderPayParam, user);
        // 订单类型
        result.put("orderType", OrderTypeEnum.MASTER.getValue());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/paySuccessDetail", method = RequestMethod.POST)
    @OperationLog(name = "paySuccessDetail")
    @Operation(summary = "paySuccessDetail")
    public ApiResult<Map<String, Object>> paySuccessDetail(Integer orderId){
        Map<String, Object> result = new HashMap<>();
        User user = this.getUser(true);
        Order order = orderService.getUserOrderDetail(orderId, user.getUserId());
        // 支付金额
        result.put("payPrice", order.getPayPrice());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @OperationLog(name = "cancel")
    @Operation(summary = "cancel")
    public ApiResult<String> cancel(Integer orderId){
        User user = this.getUser(true);
        Order order = orderService.getUserOrderDetail(orderId, user.getUserId());
        if(orderService.cancelOrder(order)){
            return ApiResult.ok(null, "取消成功");
        }else{
            return ApiResult.fail("取消失败");
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @OperationLog(name = "detail")
    @Operation(summary = "detail")
    public ApiResult<OrderDetailVo> detail(Integer orderId){
        return ApiResult.ok(userOrderService.detail(this.getUser(true),orderId));
    }

    @RequestMapping(value = "/express", method = RequestMethod.GET)
    @OperationLog(name = "express")
    @Operation(summary = "express")
    public ApiResult<ExpressDetailVo> express(Integer orderId)  throws Exception  {
        return ApiResult.ok(userOrderService.express(orderId));
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    @OperationLog(name = "receipt")
    @Operation(summary = "receipt")
    public ApiResult<String> receipt(Integer orderId){
        if(orderService.receipt(orderId)){
            return ApiResult.ok(null, "收货成功");
        }else{
            return ApiResult.fail("收货失败");
        }
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    @OperationLog(name = "qrcode")
    @Operation(summary = "qrcode")
    public ApiResult<String> qrcode(Integer orderId){
        User user = this.getUser(true);
        return ApiResult.ok(extractUtils.genePoster(orderId, user));
    }
}
