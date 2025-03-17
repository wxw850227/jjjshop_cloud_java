

package net.jjjshop.front.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 微信小程序登录参数
 */
@Data
@Accessors(chain = true)
@Schema(description = "ProductParam对象")
public class ProductParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "搜索关键字")
    private String search;

    @Schema(description = "商品分类")
    private Integer categoryId;

    @Schema(description = "排序方式")
    private String sortType;

    @Schema(description = "价格排序方式")
    private Integer sortPrice;
}
