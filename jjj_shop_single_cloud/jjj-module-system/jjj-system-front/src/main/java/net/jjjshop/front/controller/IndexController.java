

package net.jjjshop.front.controller;

import com.alibaba.fastjson.JSONObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.common.settings.vo.SysConfigVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "front-首页")
@RestController
@RequestMapping("/front/index")
public class IndexController extends BaseController {
    @Autowired
    private SettingUtils settingUtils;
    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index(Integer pageId) {
        Map<String, Object> result = new HashMap<>();
        // 页面元素
        User user = this.getUser(false);
        result.put("page", pageService.getPageData(user, pageId));
        // 设置项
        Map<String, Object> setting = new HashMap<>();
        setting.put("collection", settingUtils.getSetting(SettingEnum.COLLECTION.getKey(), null));
        setting.put("homepush", pageService.getHomePush());
        setting.put("officia", settingUtils.getSetting(SettingEnum.OFFICIA.getKey(), null));
        result.put("setting", setting);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    @OperationLog(name = "nav")
    @Operation(summary = "nav")
    public ApiResult<Map<String, Object>> nav() {
        Map<String, Object> result = new HashMap<>();
        result.put("theme", settingUtils.getSetting(SettingEnum.THEME.getKey(), null));
        result.put("nav", settingUtils.getSetting(SettingEnum.NAV.getKey(), null));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/diy", method = RequestMethod.GET)
    @OperationLog(name = "diy")
    @Operation(summary = "diy")
    public ApiResult<Map<String, Object>> diy(Integer pageId) {
        Map<String, Object> result = new HashMap<>();
        // 页面元素
        User user = this.getUser(false);
        result.put("page", pageService.getPageData(user, pageId));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/mpService", method = RequestMethod.GET)
    @OperationLog(name = "mpService")
    @Operation(summary = "mpService")
    public ApiResult<JSONObject> mpService(Long tenantId) {
        JSONObject jsonObject = settingUtils.getSetting(SettingEnum.MPSERVICE.getKey(), tenantId);
        return ApiResult.ok(jsonObject);
    }
}
