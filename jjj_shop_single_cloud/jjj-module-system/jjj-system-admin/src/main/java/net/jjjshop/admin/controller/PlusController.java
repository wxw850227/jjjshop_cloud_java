

package net.jjjshop.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.admin.service.PlusCategoryService;
import net.jjjshop.admin.service.ShopAccessService;
import net.jjjshop.admin.vo.PlusCategoryVo;
import net.jjjshop.common.entity.shop.ShopAccess;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "admin-插件设置")
@RestController
@RequestMapping("/admin/plus")
public class PlusController {

    @Autowired
    private PlusCategoryService plusCategoryService;
    @Autowired
    private ShopAccessService shopAccessService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<List<PlusCategoryVo>> index() {
        List<PlusCategoryVo> list = plusCategoryService.getAll();
        return ApiResult.ok(list);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<Boolean> delete(Integer id) {
        Boolean result = shopAccessService.editPlusCategoryId(id, 0);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<List<ShopAccess>> toAdd() {
        List<ShopAccess> list = shopAccessService.getAllPlus();
        return ApiResult.ok(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Boolean> add(Integer id,Integer plusCategoryId) {
        Boolean result = shopAccessService.editPlusCategoryId(id, plusCategoryId);
        return ApiResult.ok(result);
    }
}
