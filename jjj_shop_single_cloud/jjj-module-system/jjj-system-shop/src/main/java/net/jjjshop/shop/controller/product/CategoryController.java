package net.jjjshop.shop.controller.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.cache.ProductCategoryCache;
import net.jjjshop.common.vo.product.CategoryVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.product.CategoryParam;
import net.jjjshop.shop.service.product.ProductCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "shop-商品分类")
@RestController
@RequestMapping("/shop/product/category")
public class CategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductCategoryCache productCategoryCache;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/product/category/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<List<CategoryVo>> index() throws Exception{
        return ApiResult.ok(productCategoryCache.getCache());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/product/category/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated CategoryParam categoryParam) {
        if(productCategoryService.add(categoryParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/product/category/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated CategoryParam categoryParam) {
        if(productCategoryService.edit(categoryParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/product/category/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer categoryId) {
        if(productCategoryService.delById(categoryId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }
}
