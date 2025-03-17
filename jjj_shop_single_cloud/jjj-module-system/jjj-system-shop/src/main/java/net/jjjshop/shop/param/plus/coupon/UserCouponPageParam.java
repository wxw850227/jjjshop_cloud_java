package net.jjjshop.shop.param.plus.coupon;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 用户优惠券 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "UserCouponPageParam对象", description = "用户优惠券分页参数")
public class UserCouponPageParam extends BasePageOrderParam {
    private final static long serialVersionUID = 1L;
}
