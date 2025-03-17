package net.jjjshop.common.vo.product;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.Product;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品分类VO")
public class ProductVo extends Product {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片主图")
    private String imagePath;

    @Schema(description = "商品分类名称")
    private String categoryName;

    //编辑使用
    @Schema(description = "商品单独折扣")
    private JSONObject aloneGradeEquityJson;

    //编辑使用
    @Schema(description = "商品sku")
    private List<ProductSkuVo> sku;

    //编辑使用
    @Schema(description = "商品图片集合")
    private List<ProductImageVo> image;
}
