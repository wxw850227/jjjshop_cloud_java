package net.jjjshop.shop.param.plus.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "ArticleParam")
public class ArticleParam implements Serializable {

    private final static long serialVersionUID = 1L;

    @Schema(description = "文章id")
    private Integer articleId;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "列表显示方式(10小图展示 20大图展示)")
    private Integer showType;

    @Schema(description = "文章分类id")
    private Integer categoryId;

    @Schema(description = "封面图id")
    private Integer imageId;

    @Schema(description = "文章内容")
    private String articleContent;

    @Schema(description = "文章排序(数字越小越靠前)")
    private Integer articleSort;

    @Schema(description = "文章状态(0隐藏 1显示)")
    private Integer articleStatus;

    @Schema(description = "虚拟阅读量(仅用作展示)")
    private Integer virtualViews;

    @Schema(description = "实际阅读量")
    private Integer actualViews;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "描述")
    private String description;

}
