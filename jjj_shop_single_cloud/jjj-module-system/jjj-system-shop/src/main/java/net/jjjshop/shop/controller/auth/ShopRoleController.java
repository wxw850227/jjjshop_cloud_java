package net.jjjshop.shop.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.shop.ShopRole;
import net.jjjshop.common.util.ShopAccessUtils;
import net.jjjshop.common.vo.shop.ShopAccessVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.shopUser.ShopRoleParam;
import net.jjjshop.shop.service.shop.ShopRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "shop-角色")
@RestController
@RequestMapping("/shop/auth/role")
public class ShopRoleController {
    @Autowired
    private ShopRoleService shopRoleService;
    @Autowired
    private ShopAccessUtils shopAccessUtils;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/auth/role/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<List<ShopRole>> index() throws Exception {
        return ApiResult.ok(shopRoleService.getList());
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    @RequiresPermissions("/auth/role/add")
    @OperationLog(name = "toAdd")
    @Operation(summary = "toAdd")
    public ApiResult<List<ShopAccessVo>> toAdd() {
        return ApiResult.ok(shopAccessUtils.getShopAll());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/auth/role/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Boolean> add(@Validated @RequestBody ShopRoleParam shopRoleParam) {
        return ApiResult.ok(shopRoleService.add(shopRoleParam));
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/auth/role/edit")
    @OperationLog(name = "toEdit")
    @Operation(summary = "toEdit")
    public ApiResult<Map<String, Object>> toEdit(Integer roleId) {
        Map<String, Object> result = new HashMap<>();
        result.put("menu", shopAccessUtils.getShopAll());
        List<Integer> selectMenu = shopRoleService.getSelectList(roleId);
        result.put("selectMenu", selectMenu);
        result.put("model", shopRoleService.getById(roleId));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/auth/role/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Boolean> edit(@Validated @RequestBody ShopRoleParam shopRoleParam) {
        return ApiResult.ok(shopRoleService.edit(shopRoleParam));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/auth/role/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<Boolean> delete(Integer roleId) {
        return ApiResult.ok(shopRoleService.delete(roleId));
    }
}
