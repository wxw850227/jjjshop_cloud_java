

package net.jjjshop.admin.controller;

import com.alibaba.fastjson.JSONObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.common.settings.vo.SysConfigVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.config.properties.SpringBootJjjProperties;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "admin-首页")
@RestController
@RequestMapping("/admin/index")
public class IndexController {
    @Autowired
    private SettingUtils settingUtils;
    @Autowired
    private SpringBootJjjProperties springBootJjjProperties;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @Operation(summary = "index")
    @OperationLog(name = "index")
    public ApiResult<String> index() throws IOException {
        log.debug("index...");
        return ApiResult.ok(springBootJjjProperties.getProjectVersion());
    }

    @RequestMapping(value = "/base", method = RequestMethod.POST)
    @OperationLog(name = "base")
    @Operation(summary = "base")
    public ApiResult<Map<String, Object>> base() {
        Map<String, Object> result = new HashMap<String, Object>();
        String codeImage = "";
        String codeKey = "";
        boolean adminLoginCode = false;
        JSONObject jsonData = settingUtils.getSetting(SettingEnum.SYS_CONFIG.getKey(), 0L);
        if (jsonData != null) {
            SysConfigVo sysConfigVo = JSONObject.toJavaObject(jsonData, SysConfigVo.class);
            //登录验证码开关,true开,false关
            if (sysConfigVo != null && sysConfigVo.getAdminLoginCode()) {
                LoginParam param = settingUtils.generateValidateCode();
                if (param != null) {
                    adminLoginCode = true;
                    codeKey = param.getCodeKey();
                    codeImage = param.getCodeImage();
                }
            }
        }
        result.put("codeImage", codeImage);
        result.put("codeKey", codeKey);
        result.put("adminLoginCode", adminLoginCode);
        return ApiResult.ok(result);
    }
}
