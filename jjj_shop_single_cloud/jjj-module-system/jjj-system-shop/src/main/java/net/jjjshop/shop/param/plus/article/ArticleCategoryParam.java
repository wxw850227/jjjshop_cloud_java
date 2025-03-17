package net.jjjshop.shop.param.plus.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "ArticleParam对象", description = "文章参数对象")
public class ArticleCategoryParam implements Serializable {
    private final static long serialVersionUID = 1L;

    @Schema(description = "商品分类id")
    private Integer categoryId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "排序方式(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;

}
