

package net.jjjshop.shop.controller.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.service.product.ProductSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "shop-商品规格")
@RestController
@RequestMapping("/shop/product/spec")
public class SpecController {
    @Autowired
    private ProductSpecService productSpecService;

    @RequestMapping(value = "/addSpec", method = RequestMethod.POST)
    //@RequiresPermissions("/product/product/add")
    @OperationLog(name = "addSpec")
    @Operation(summary = "addSpec")
    public ApiResult<Map<String,Object>> addSpec(String specName, String specValue) throws Exception{
        return ApiResult.ok(productSpecService.addSpec(specName, specValue));
    }

    @RequestMapping(value = "/addSpecValue", method = RequestMethod.POST)
    //@RequiresPermissions("/product/product/add")
    @OperationLog(name = "addSpecValue")
    @Operation(summary = "addSpecValue")
    public ApiResult<Map<String,Object>> addSpecValue(Integer specId, String specValue) throws Exception{
        return ApiResult.ok(productSpecService.addSpecValue(specId, specValue));
    }
}
