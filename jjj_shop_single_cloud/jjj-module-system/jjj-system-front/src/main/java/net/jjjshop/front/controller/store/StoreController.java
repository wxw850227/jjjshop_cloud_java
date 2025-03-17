package net.jjjshop.front.controller.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.service.store.StoreService;
import net.jjjshop.front.vo.store.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "front-门店")
@RestController
@RequestMapping("/front/store/store")
public class StoreController extends BaseController {
    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<List<StoreVo>> recommendProduct(String longitude, String latitude) throws Exception{
        return ApiResult.ok(storeService.getAllCheck(longitude, latitude));
    }
}
