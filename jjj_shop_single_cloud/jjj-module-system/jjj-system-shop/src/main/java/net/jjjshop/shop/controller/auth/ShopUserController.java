

package net.jjjshop.shop.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.app.App;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.config.properties.SpringBootJjjProperties;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.framework.shiro.vo.LoginShopAccessVo;
import net.jjjshop.framework.shiro.vo.LoginShopUserRedisVo;
import net.jjjshop.framework.util.ShopLoginUtil;
import net.jjjshop.shop.param.shopUser.ShopUserPageParam;
import net.jjjshop.shop.param.shopUser.ShopUserParam;
import net.jjjshop.shop.service.app.AppService;
import net.jjjshop.shop.service.shop.ShopRoleService;
import net.jjjshop.shop.service.shop.ShopUserService;
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
@Tag(name = "shop-用户")
@RestController
@RequestMapping("/shop/auth/user")
public class ShopUserController {
    @Autowired
    private ShopUserService shopUserService;
    @Autowired
    private ShopRoleService shopRoleService;
    @Autowired
    private SettingUtils settingUtils;
    @Autowired
    private SpringBootJjjProperties springBootJjjProperties;
    @Autowired
    private AppService appService;

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    @OperationLog(name = "getRoleList")
    @Operation(summary = "getRoleList")
    public ApiResult<List<LoginShopAccessVo>> getRoleList() throws Exception {
        LoginShopUserRedisVo loginShopUserRedisVo = ShopLoginUtil.getLoginShopUserRedisVo();
        return ApiResult.ok(loginShopUserRedisVo.getMenus());
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @OperationLog(name = "getUserInfo")
    @Operation(summary = "getUserInfo")
    public ApiResult<Map<String, Object>> getUserInfo() {
        Map<String, Object> result = new HashMap<String, Object>();
        // 登录用户名
        result.put("username", ShopLoginUtil.getUsername());
        // 版本号
        result.put("version", springBootJjjProperties.getProjectVersion());
        // 商城名称
        App app = appService.getById(ShopLoginUtil.getTenantId());
        if (app != null) {
            result.put("shop_name", app.getAppName());
        } else {
            result.put("shop_name", "");
        }
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/auth/user/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index(@Validated @RequestBody ShopUserPageParam params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("userList", shopUserService.getList(params));
        result.put("roleList", shopRoleService.getList());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/auth/user/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Boolean> add(@Validated @RequestBody ShopUserParam shopUserParam) {
        return ApiResult.ok(shopUserService.add(shopUserParam));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/auth/user/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Boolean> edit(@Validated @RequestBody ShopUserParam shopUserParam) {
        return ApiResult.ok(shopUserService.edit(shopUserParam));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/auth/user/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<Boolean> delete(Integer shopUserId) {
        return ApiResult.ok(shopUserService.setDelete(shopUserId));
    }
}
