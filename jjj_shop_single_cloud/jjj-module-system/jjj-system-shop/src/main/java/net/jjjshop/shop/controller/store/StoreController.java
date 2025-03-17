package net.jjjshop.shop.controller.store;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.vo.RegionVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.CommonPageParam;
import net.jjjshop.shop.param.store.StoreParam;
import net.jjjshop.shop.service.store.StoreService;
import net.jjjshop.shop.vo.store.StoreVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "shop-门店列表")
@RestController
@RequestMapping("/shop/store/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/store/store/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<StoreVo>> index(@Validated @RequestBody CommonPageParam commonPageParam) throws Exception {
        return ApiResult.ok(storeService.getList(commonPageParam));
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    @RequiresPermissions("/store/store/add")
    @OperationLog(name = "toAdd")
    @Operation(summary = "toAdd")
    public ApiResult<List<RegionVo>> toAdd() throws Exception {
        return ApiResult.ok(storeService.toAdd());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/store/store/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@Validated @RequestBody StoreParam storeParam) throws Exception {
        if(storeService.add(storeParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/store/store/edit")
    @OperationLog(name = "toEdit")
    @Operation(summary = "toEdit")
    public ApiResult<Map<String, Object>> toEdit(Integer storeId) throws Exception {
        return ApiResult.ok(storeService.toEdit(storeId));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/store/store/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@Validated @RequestBody StoreParam storeParam) throws Exception {
        if(storeService.edit(storeParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/store/store/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer storeId) throws Exception {
        if(storeService.setDelete(storeId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }
}
