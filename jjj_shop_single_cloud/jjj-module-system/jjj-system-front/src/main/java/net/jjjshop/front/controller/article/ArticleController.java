package net.jjjshop.front.controller.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.plus.article.ArticleCategory;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.service.plus.article.ArticleCategoryService;
import net.jjjshop.front.service.plus.article.ArticleService;
import net.jjjshop.front.vo.plus.article.ArticleDetailVo;
import net.jjjshop.front.vo.plus.article.ArticleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "front-文章")
@RestController
@RequestMapping("/front/plus/article/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<ArticleListVo>> index(Integer pageIndex, Integer pageSize, Integer categoryId) {
        return ApiResult.ok(articleService.getList(pageIndex, pageSize, categoryId));
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @OperationLog(name = "category")
    @Operation(summary = "category")
    public ApiResult<List<ArticleCategory>> category() {
        return ApiResult.ok(articleCategoryService.getAll());
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @OperationLog(name = "detail")
    @Operation(summary = "detail")
    public ApiResult<ArticleDetailVo> detail(Integer articleId) {
        return ApiResult.ok(articleService.getDetail(articleId));
    }

}
