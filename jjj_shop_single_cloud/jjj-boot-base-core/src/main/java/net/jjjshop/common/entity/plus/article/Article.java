package net.jjjshop.common.entity.plus.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章记录表
 *
 * @author jjjshop
 * @since 2022-07-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Article对象")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文章id")
    @TableId(value = "article_id", type = IdType.AUTO)
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
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "描述")
    private String description;


}
