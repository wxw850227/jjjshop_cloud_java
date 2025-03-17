package net.jjjshop.front.util.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 临时商品购买vo，用于计算
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "临时商品购买vo")
public class TempProductBuyVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "产品id")
    private Integer productId;

    @Schema(description = "商品总价")
    private BigDecimal totalPrice;

    @Schema(description = "商品总价,单位分")
    private BigDecimal totalPriceFen;

    @Schema(description = "权重")
    private BigDecimal weight;

    @Schema(description = "优惠券抵扣金额")
    private BigDecimal couponMoney;
}
