package net.jjjshop.shop.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.store.StoreOrderPageParam;
import net.jjjshop.shop.service.store.StoreOrderService;
import net.jjjshop.shop.service.store.StoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "shop-订单核销列表")
@RestController
@RequestMapping("/shop/store/order")
public class StoreOrderController {

    @Autowired
    private StoreOrderService storeOrderService;

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/store/order/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index(@Validated @RequestBody StoreOrderPageParam storeOrderPageParam) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("storeList", storeService.getAll());
        map.put("list", storeOrderService.getList(storeOrderPageParam));
        return ApiResult.ok(map);
    }
}
