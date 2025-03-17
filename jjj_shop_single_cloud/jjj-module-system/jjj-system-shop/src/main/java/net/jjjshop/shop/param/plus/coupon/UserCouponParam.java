package net.jjjshop.shop.param.plus.coupon;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户优惠券 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "UserCouponParam对象", description = "用户优惠券参数")
public class UserCouponParam implements Serializable {
    private final static long serialVersionUID = 1L;
}
