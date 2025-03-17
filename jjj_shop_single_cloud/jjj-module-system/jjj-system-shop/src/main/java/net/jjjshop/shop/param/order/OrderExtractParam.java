package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "OrderExtractParam对象", description = "订单核销参数")
public class OrderExtractParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "核销状态")
    private Integer extractStatus;

    @Schema(description = "订单Id")
    private Integer orderId;

    @Schema(description = "核销员")
    private Integer extractClerkId;
}
