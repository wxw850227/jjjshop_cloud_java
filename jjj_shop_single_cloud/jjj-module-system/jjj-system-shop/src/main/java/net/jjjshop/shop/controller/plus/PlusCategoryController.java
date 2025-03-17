package net.jjjshop.shop.controller.plus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.service.plus.plus.PlusCategoryService;
import net.jjjshop.shop.vo.plus.PlusCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "shop-插件中心")
@RestController
@RequestMapping("/shop/plus/plus")
public class PlusCategoryController {

    @Autowired
    private PlusCategoryService plusCategoryService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<List<PlusCategoryVo>> index() throws Exception{
        return ApiResult.ok(plusCategoryService.getAll());
    }
}
