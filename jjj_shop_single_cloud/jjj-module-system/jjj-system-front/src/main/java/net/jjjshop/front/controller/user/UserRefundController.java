package net.jjjshop.front.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.param.order.OrderRefundDeliveryParam;
import net.jjjshop.front.param.order.OrderRefundPageParam;
import net.jjjshop.front.param.order.OrderRefundParam;
import net.jjjshop.front.service.order.OrderProductService;
import net.jjjshop.front.service.order.OrderRefundService;
import net.jjjshop.front.service.user.UserOrderService;
import net.jjjshop.front.vo.order.OrderRefundApplyVo;
import net.jjjshop.front.vo.order.OrderRefundDetailVo;
import net.jjjshop.front.vo.order.OrderRefundListVo;
import net.jjjshop.front.vo.settings.ExpressDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "front-售后")
@RestController
@RequestMapping("/front/user/refund")
public class UserRefundController extends BaseController {

    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private OrderProductService orderProductService;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<Paging<OrderRefundListVo>> lists(@RequestBody OrderRefundPageParam orderRefundPageParam){
        orderRefundPageParam.setUserId(this.getUser(true).getUserId());
        return ApiResult.ok(orderRefundService.getList(orderRefundPageParam));
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @OperationLog(name = "apply")
    @Operation(summary = "apply")
    public ApiResult<String> apply(@RequestBody OrderRefundParam orderRefundParam){
        if(orderRefundService.apply(orderRefundParam)){
            return ApiResult.ok(null, "申请售后成功");
        }else{
            return ApiResult.fail("申请售后失败");
        }
    }

    @RequestMapping(value = "/toApply", method = RequestMethod.GET)
    @OperationLog(name = "toApply")
    @Operation(summary = "toApply")
    public ApiResult<OrderRefundApplyVo> toApply(Integer orderProductId){
        return ApiResult.ok(orderRefundService.toApply(orderProductId));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @OperationLog(name = "detail")
    @Operation(summary = "detail")
    public ApiResult<OrderRefundDetailVo> detail(Integer orderRefundId){
        return ApiResult.ok(orderRefundService.detail(orderRefundId));
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @OperationLog(name = "delivery")
    @Operation(summary = "delivery")
    public ApiResult<String> delivery(@RequestBody OrderRefundDeliveryParam orderRefundDeliveryParam){
        if(orderRefundService.delivery(orderRefundDeliveryParam)){
            return ApiResult.ok(null, "售后发货成功");
        }else{
            return ApiResult.fail("售后发货失败");
        }
    }

    @RequestMapping(value = "/express", method = RequestMethod.GET)
    @OperationLog(name = "express")
    @Operation(summary = "express")
    public ApiResult<ExpressDetailVo> express(Integer orderRefundId)  throws Exception  {
        return ApiResult.ok(orderRefundService.express(orderRefundId));
    }


}
