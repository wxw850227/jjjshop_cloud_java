package net.jjjshop.shop.controller.setting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.Express;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.setting.ExpressPageParam;
import net.jjjshop.shop.param.setting.ExpressParam;
import net.jjjshop.shop.service.settings.ExpressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-物流公司")
@RestController
@RequestMapping("/shop/setting/express")
public class ExpressController {
    @Autowired
    private ExpressService expressService;


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/setting/express/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<Express>> index(@RequestBody @Validated ExpressPageParam expressPageParam) throws Exception {
        return ApiResult.ok(expressService.getList(expressPageParam));
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/setting/express/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated ExpressParam expressParam) {
        if (expressService.add(expressParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/setting/express/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated ExpressParam expressParam) {
        if (expressService.edit(expressParam)) {
            return ApiResult.ok(null, "修改成功");
        } else {
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/setting/express/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult toEdit(Integer expressId) {
        return ApiResult.ok(expressService.getById(expressId));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/setting/express/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult  delById(Integer id) {
        if (expressService.delById(id)) {
            return ApiResult.ok("删除成功");
        } else {
            return ApiResult.fail("删除失败");
        }
    }

}
