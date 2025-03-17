package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "售后单参数")
public class OrderRefundParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "审核状态")
    private Integer isAgree;

    @Schema(description = "orderRefundId")
    private Integer orderRefundId;

    @Schema(description = "拒绝原因")
    private String refuseDesc;

    @Schema(description = "售后发货地址")
    private Integer addressId;

    @Schema(description = "售后单换货物流Id")
    private Integer sendExpressId;

    @Schema(description = "售后单换货物流编号")
    private String sendExpressNo;

    @Schema(description = "退款金额")
    private BigDecimal refundMoney;

}
