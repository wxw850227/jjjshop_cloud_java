

package net.jjjshop.shop.param.product;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProductParam对象", description = "商品查询参数")
public class ProductPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "tab栏选中的类型")
    private String type;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "产品规格(10单规格 20多规格)")
    private Integer specType;

}
