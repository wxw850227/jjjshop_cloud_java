package net.jjjshop.shop.vo.plus.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.plus.article.Article;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "文章Vo")
public class ArticleVo extends Article {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "图片url")
    private String imageUrl;
}
