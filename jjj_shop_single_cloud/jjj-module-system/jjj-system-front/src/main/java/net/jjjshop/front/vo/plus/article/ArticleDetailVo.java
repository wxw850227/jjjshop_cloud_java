package net.jjjshop.front.vo.plus.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description ="文章详情Vo")
public class ArticleDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文章id")
    private Integer articleId;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "列表显示方式(10小图展示 20大图展示)")
    private Integer showType;

    @Schema(description = "文章分类id")
    private Integer categoryId;

    @Schema(description = "文章内容")
    private String articleContent;

    @Schema(description = "文章排序(数字越小越靠前)")
    private Integer articleSort;

    @Schema(description = "文章状态(0隐藏 1显示)")
    private Integer articleStatus;

    @Schema(description = "虚拟阅读量(仅用作展示)")
    private Integer virtualViews;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "图片路径")
    private String filePath;

    @Schema(description = "浏览量")
    private Integer viewTime;

    @Schema(description = "创建时间")
    private Date createTime;
}
