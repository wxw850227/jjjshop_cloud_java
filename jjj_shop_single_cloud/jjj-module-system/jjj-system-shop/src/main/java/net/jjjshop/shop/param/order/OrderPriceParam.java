package net.jjjshop.shop.param.order;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "订单参数")
public class OrderPriceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单Id")
    private Integer orderId;

    @Schema(description = "订单金额")
    private BigDecimal updatePrice;

    @Schema(description = "运费金额")
    private BigDecimal updateExpressPrice;

}
