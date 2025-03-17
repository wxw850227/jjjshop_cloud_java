package net.jjjshop.front.controller.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.service.plus.coupon.CouponService;
import net.jjjshop.front.vo.coupon.CouponDetailVo;
import net.jjjshop.front.vo.coupon.CouponListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "front-优惠券")
@RestController
@RequestMapping("/front/coupon/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<List<CouponListVo>> index() {
        return ApiResult.ok(couponService.getWaitList(this.getUser(false), 0));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @OperationLog(name = "detail")
    @Operation(summary = "detail")
    public ApiResult<CouponDetailVo> detail(Integer couponId) {
        return ApiResult.ok(couponService.detail(couponId,this.getUser(false)));
    }

}
