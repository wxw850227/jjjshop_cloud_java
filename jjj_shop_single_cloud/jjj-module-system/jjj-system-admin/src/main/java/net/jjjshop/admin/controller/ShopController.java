

package net.jjjshop.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.admin.param.AppPageParam;
import net.jjjshop.admin.param.AppParam;
import net.jjjshop.admin.service.AppService;
import net.jjjshop.admin.vo.AppVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "admin-商城管理")
@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<AppVo>> index(@Validated @RequestBody AppPageParam appPageParam) {
        return ApiResult.ok(appService.getList(appPageParam));
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @OperationLog(name = "updateStatus")
    @Operation(summary = "updateStatus")
    public ApiResult<String> updateStatus(Integer tenantId) {
        if(appService.editStatusById(tenantId)) {
            return ApiResult.ok(null, "更新成功");
        }else{
            return ApiResult.fail("更新失败");
        }
    }

    @RequestMapping(value = "/updateWxStatus", method = RequestMethod.POST)
    @OperationLog(name = "updateWxStatus")
    @Operation(summary = "updateWxStatus")
    public ApiResult<String> updateWxStatus(Integer tenantId) {
        if(appService.updateWxStatus(tenantId)) {
            return ApiResult.ok(null, "更新成功");
        }else{
            return ApiResult.fail("更新失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer tenantId) {
        if(appService.setDelete(tenantId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated AppParam appParam) {
        if(appService.add(appParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated AppParam appParam) {
        if(appService.edit(appParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }
}
