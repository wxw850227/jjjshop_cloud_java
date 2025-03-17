package net.jjjshop.front.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.cache.RegionCache;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.service.settings.RegionService;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "front-设置")
@RestController
@RequestMapping("/front/settings")
public class SettingsController extends BaseController {

    @Autowired
    private RegionCache regionCache;
    @Autowired
    private RegionService regionService;
    @Autowired
    private SettingUtils settingUtils;

    @RequestMapping(value = "/getRegion", method = RequestMethod.POST)
    @OperationLog(name = "getRegion")
    @Operation(summary = "getRegion")
    public ApiResult<List<JSONArray>> getRegion() {
        return ApiResult.ok(regionService.getRegionForApi());
    }

    @RequestMapping(value = "/appShare", method = RequestMethod.GET)
    @OperationLog(name = "appShare")
    @Operation(summary = "app分享参数")
    public ApiResult<JSONObject> appShare() {
        return ApiResult.ok(settingUtils.getSetting(SettingEnum.APP_SHARE.getKey(), null));
    }
}
