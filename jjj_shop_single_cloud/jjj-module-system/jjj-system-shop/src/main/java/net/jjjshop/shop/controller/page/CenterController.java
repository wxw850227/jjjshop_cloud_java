

package net.jjjshop.shop.controller.page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.page.CenterMenu;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.CommonPageParam;
import net.jjjshop.shop.param.page.CenterParam;
import net.jjjshop.shop.service.page.CenterMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-个人中心")
@RestController
@RequestMapping("/shop/page/center")
public class CenterController {
    @Autowired
    private CenterMenuService centerMenuService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/page/center/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<CenterMenu>> index(@Validated @RequestBody CommonPageParam commonPageParam) throws Exception{
        return ApiResult.ok(centerMenuService.getList(commonPageParam));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/page/center/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated CenterParam centerParam) {
        if(centerMenuService.add(centerParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @RequiresPermissions("/page/center/edit")
    @OperationLog(name = "status")
    @Operation(summary = "status")
    public ApiResult<String> status(Integer menuId, Integer isShow) {
        if(centerMenuService.status(menuId, isShow)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @RequiresPermissions("/page/center/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<CenterMenu> edit(Integer menuId) {
        return ApiResult.ok(centerMenuService.getById(menuId));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/page/center/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated CenterParam centerParam) {
        if(centerMenuService.edit(centerParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/page/center/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer menuId) {
        if(centerMenuService.delById(menuId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }
}
