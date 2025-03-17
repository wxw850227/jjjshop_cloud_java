package net.jjjshop.common.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.shop.ProductSpecRel;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品规格VO")
public class ProductSpecRelVo extends ProductSpecRel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "规格名称")
    private String specName;

    @Schema(description = "规格值")
    private String specValue;
}
