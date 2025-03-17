package net.jjjshop.shop.controller.setting;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.ReturnAddress;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.setting.ReturnAddressPageParam;
import net.jjjshop.shop.param.setting.ReturnAddressParam;
import net.jjjshop.shop.service.settings.ReturnAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-地址设置")
@RestController
@RequestMapping("/shop/setting/address")
public class ReturnAddressController {

    @Autowired
    private ReturnAddressService returnAddressService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/setting/address/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<ReturnAddress>> index(@RequestBody @Validated ReturnAddressPageParam returnAddressPageParam) throws Exception {
        return ApiResult.ok(returnAddressService.getList(returnAddressPageParam));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/setting/address/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated ReturnAddressParam returnAddressParam) {
        if (returnAddressService.add(returnAddressParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }

    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/setting/address/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult toEdit(Integer addressId) {
        return ApiResult.ok(returnAddressService.getById(addressId));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/setting/address/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated ReturnAddressParam returnAddressParam) {
        if (returnAddressService.edit(returnAddressParam)) {
            return ApiResult.ok(null, "修改成功");
        } else {
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/setting/address/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult setDelete(Integer id) {
        if (returnAddressService.setDelete(id)) {
            return ApiResult.ok("删除成功");
        } else {
            return ApiResult.fail("删除失败");
        }
    }
}
