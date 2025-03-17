package net.jjjshop.shop.controller.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.product.CommentPageParam;
import net.jjjshop.shop.param.product.CommentParam;
import net.jjjshop.shop.service.product.ProductCommentService;
import net.jjjshop.shop.vo.product.CommentVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "shop-商品评价")
@RestController
@RequestMapping("/shop/product/comment")
public class CommentController {

    @Autowired
    private ProductCommentService productCommentService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/product/comment/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index(@Validated @RequestBody CommentPageParam commentPageParam) {
        return ApiResult.ok(productCommentService.getList(commentPageParam));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @RequiresPermissions("/product/comment/detail")
    @OperationLog(name = "detail")
    @Operation(summary = "detail")
    public ApiResult<CommentVo> detail(Integer commentId) {
        return ApiResult.ok(productCommentService.getDetail(commentId));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/product/comment/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@Validated @RequestBody CommentParam commentParam) {
        if (productCommentService.edit(commentParam)) {
            return ApiResult.ok(null, "修改成功");
        } else {
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/product/comment/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer commentId) {
        if (productCommentService.setDelete(commentId)) {
            return ApiResult.ok(null, "修改成功");
        } else {
            return ApiResult.fail("修改失败");
        }
    }
}
