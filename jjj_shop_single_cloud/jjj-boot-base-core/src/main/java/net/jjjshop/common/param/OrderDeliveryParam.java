

package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 订单物流参数
 */
@Data
@Accessors(chain = true)
@Schema(name = "订单物流参数对象", description = "订单物流参数对象")
public class OrderDeliveryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单物流id")
    private Integer orderDeliveryId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "物流公司id")
    @NotNull(message = "物流公司id不能为空")
    @Min(value = 1, message = "物流公司id不能为空")
    private Integer expressId;

    @Schema(description = "物流单号")
    @NotEmpty(message = "物流单号不能为空")
    private String expressNo;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "物流商品信息json格式，如商品id和数量")
    private String deliveryData;

    @Schema(description = "tenantId")
    private Integer tenantId;

    @Schema(description = "recordId")
    private Integer recordId;
}
