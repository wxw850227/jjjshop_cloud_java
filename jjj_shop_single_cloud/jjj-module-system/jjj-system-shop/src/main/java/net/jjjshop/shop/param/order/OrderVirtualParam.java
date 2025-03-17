package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "订单虚拟商品参数")
public class OrderVirtualParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单商品")
    private Integer orderId;

    @Schema(description = "虚拟商品发货信息")
    private String virtualContent;
}

