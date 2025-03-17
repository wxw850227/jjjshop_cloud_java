package net.jjjshop.shop.param.plus.coupon;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(name = "SendCouponParam对象", description = "发送优惠券参数")
public class SendCouponParam implements Serializable {
    private final static long serialVersionUID = 1L;

    @Schema(description = "优惠券名字")
    private String name;

    @Schema(description = "发送类型")
    private Integer sendType;

    @Schema(description = "用户Id")
    private List<Integer> userIds;

    @Schema(description = "用户等级")
    private Integer userLevel;

    @Schema(description = "优惠券Id")
    private Integer couponId;
}
