package net.jjjshop.front.vo.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "首页推送优惠券VO")
public class HomeCouponVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "优惠券id")
    private Integer couponId;

    @Schema(description = "优惠券名称")
    private String name;

    @Schema(description = "优惠券类型(10满减券 20折扣券)")
    private Integer couponType;

    @Schema(description = "满减券-减免金额")
    private BigDecimal reducePrice;

    @Schema(description = "折扣券-折扣率(0-100)")
    private Integer discount;

    @Schema(description = "最低消费金额")
    private BigDecimal minPrice;
}
