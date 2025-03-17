package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "OrderRefundDeliveryParam")
public class OrderRefundDeliveryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "物流编号")
    private String expressNo;

    @Schema(description = "物流公司Id")
    private Integer expressId;

    @Schema(description = "售后单Id")
    private Integer orderRefundId;

}
