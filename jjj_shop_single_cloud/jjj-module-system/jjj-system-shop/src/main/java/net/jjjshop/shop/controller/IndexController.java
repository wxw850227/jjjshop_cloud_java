

package net.jjjshop.shop.controller;

import com.alibaba.fastjson.JSONObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.param.HomeParam;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.common.settings.vo.SysConfigVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.service.statistics.HomeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "shop-首页")
@RestController
@RequestMapping("/shop/index")
public class IndexController {
    @Autowired
    private SettingUtils settingUtils;
    @Autowired
    private HomeDataService homeDataService;

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @OperationLog(name = "base")
    @Operation(summary = "base")
    public ApiResult<Map<String, Object>> base() {
        JSONObject vo = settingUtils.getSetting(SettingEnum.SYS_CONFIG.getKey(), null);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("shopName", vo.getString("shopName"));
        result.put("shopBgImg", vo.getString("shopBgImg"));
        result.put("shopLogoImg", vo.getString("shopLogoImg"));
        String codeImage = "";
        String codeKey = "";
        boolean loginCode = false;
        SysConfigVo sysConfigVo = JSONObject.toJavaObject(vo, SysConfigVo.class);
        //登录验证码开关,true开,false关
        if (sysConfigVo != null && sysConfigVo.getLoginCode()) {
            LoginParam param = settingUtils.generateValidateCode();
            if (param != null) {
                loginCode = true;
                codeKey = param.getCodeKey();
                codeImage = param.getCodeImage();
            }
        }
        result.put("codeImage", codeImage);
        result.put("codeKey", codeKey);
        result.put("LoginCode", loginCode);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<JSONObject> index(@RequestBody HomeParam param) throws Exception {
        return ApiResult.ok(homeDataService.getHomeData(param));
    }

    //登录设置
    @RequestMapping(value = "/loginSetting", method = RequestMethod.GET)
    @OperationLog(name = "loginSetting")
    @Operation(summary = "loginSetting")
    public ApiResult<JSONObject> loginSetting() {
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORE.getKey(), null);
        return ApiResult.ok(vo);
    }
}
