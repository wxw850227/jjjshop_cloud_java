package net.jjjshop.shop.controller.data;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.product.ProductPageParam;
import net.jjjshop.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "shop-商品数据")
@RestController
@RequestMapping("/shop/data/product")
public class ProductDataController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<Map<String,Object>> index(@Validated @RequestBody ProductPageParam productPageParam) throws Exception{
        return ApiResult.ok(productService.getList(productPageParam));
    }
}
