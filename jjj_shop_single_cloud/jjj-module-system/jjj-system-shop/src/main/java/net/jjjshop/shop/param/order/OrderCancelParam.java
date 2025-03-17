package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "订单取消参数")
public class OrderCancelParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "备注")
    private String cancelRemark;
}

