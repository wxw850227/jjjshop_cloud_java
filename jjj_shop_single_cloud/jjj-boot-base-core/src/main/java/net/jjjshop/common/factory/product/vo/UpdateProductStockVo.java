package net.jjjshop.common.factory.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品减库存vo")
public class UpdateProductStockVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "产品id")
    private Integer productId;

    @Schema(description = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    @Schema(description = "购买数量")
    private Integer totalNum;

    @Schema(description = "购买规格")
    private String specSkuId;
}
