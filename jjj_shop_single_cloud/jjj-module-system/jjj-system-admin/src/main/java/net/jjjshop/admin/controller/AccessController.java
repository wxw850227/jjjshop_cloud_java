

package net.jjjshop.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.admin.param.ShopAccessParam;
import net.jjjshop.admin.service.ShopAccessService;
import net.jjjshop.common.util.ShopAccessUtils;
import net.jjjshop.common.vo.shop.ShopAccessVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "admin-权限")
@RestController
@RequestMapping("/admin/access")
public class AccessController {
    @Autowired
    private ShopAccessService shopAccessService;
    @Autowired
    private ShopAccessUtils shopAccessUtils;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<List<ShopAccessVo>> index() {
        List<ShopAccessVo> list = shopAccessUtils.getAll();
        return ApiResult.ok(list);
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @OperationLog(name = "status")
    @Operation(summary = "status")
    public ApiResult<Boolean> status(Integer id,Integer status) {
        Boolean result = shopAccessService.editStatusById(id, status);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer id) {
        if(shopAccessService.delById(id)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated ShopAccessParam shopAccessParam) {
        if(shopAccessService.add(shopAccessParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated ShopAccessParam shopAccessParam) {
        if(shopAccessService.edit(shopAccessParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }
}
