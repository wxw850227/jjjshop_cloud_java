package net.jjjshop.common.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.ProductCategory;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品分类VO")
public class CategoryVo extends ProductCategory {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类图片路径")
    private String imagePath;

    @Schema(description = "子分类集合")
    private List<CategoryVo> children;

    @Schema(description = "父类名称")
    private String parent;
}
