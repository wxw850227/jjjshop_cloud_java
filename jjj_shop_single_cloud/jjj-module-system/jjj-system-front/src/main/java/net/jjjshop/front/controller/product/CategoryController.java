package net.jjjshop.front.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.cache.ProductCategoryCache;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "front-商品分类")
@RestController
@RequestMapping("/front/product/category")
public class CategoryController {

    @Autowired
    private ProductCategoryCache productCategoryCache;
    @Autowired
    private SettingUtils settingUtils;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index() throws Exception{
        Map<String, Object> result = new HashMap<>();
        result.put("list", productCategoryCache.getCache());
        result.put("template", settingUtils.getSetting(SettingEnum.PAGE_CATEGORY.getKey(), null));
        return ApiResult.ok(result);
    }
}
