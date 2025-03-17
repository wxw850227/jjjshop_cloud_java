package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "OrderParam对象", description = "订单参数")
public class OrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单Id")
    private Integer orderId;

    @Schema(description = "物流公司Id")
    private Integer expressId;

    @Schema(description = "物流编号")
    private String expressNo;

    @Schema(description = "是否取消")
    private Boolean isCancel;
}
