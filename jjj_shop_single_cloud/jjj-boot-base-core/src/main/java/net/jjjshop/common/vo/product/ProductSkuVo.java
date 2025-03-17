package net.jjjshop.common.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.ProductSku;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品分类VO")
public class ProductSkuVo extends ProductSku {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片主图")
    private String imagePath;

    @Schema(description = "多规格文字")
    private String productAttr;
}
