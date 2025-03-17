

package net.jjjshop.shop.controller.product;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.product.ProductPageParam;
import net.jjjshop.shop.param.product.ProductParam;
import net.jjjshop.shop.service.product.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "shop-商品列表")
@RestController
@RequestMapping("/shop/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/product/product/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    //@SentinelResource("productIndex")
    public ApiResult<Map<String,Object>> index(@Validated @RequestBody ProductPageParam productPageParam) throws Exception{
        return ApiResult.ok(productService.getList(productPageParam));
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    @RequiresPermissions("/product/product/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Map<String, Object>> toAdd() {
        return ApiResult.ok(productService.getBaseData(0, "add"));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/product/product/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated ProductParam productParam) {
        if(productService.add(productParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @RequiresPermissions("/product/product/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Map<String, Object>> toEdit(Integer productId, String scene) {
        return ApiResult.ok(productService.getBaseData(productId, scene));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/product/product/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated ProductParam productParam) {
        if(productService.edit(productParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    @RequiresPermissions("/product/product/state")
    @OperationLog(name = "state")
    @Operation(summary = "state")
    public ApiResult<String> state(Integer productId, Integer state) {
        if(productService.setState(productId, state)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/product/product/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer productId) {
        if(productService.setDelete(productId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }
}
