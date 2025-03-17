package net.jjjshop.front.controller.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.settings.vo.StoreVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name =  "front-隐私协议")
@RestController
@RequestMapping("/front/user/userApple")
public class UserAppleController extends BaseController {

    @Autowired
    private SettingUtils settingUtils;


    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @OperationLog(name = "policy")
    @Operation(summary = "policy")
    public ApiResult<StoreVo.Policy> policy() {
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORE.getKey(), null);
        StoreVo storeVo = JSONObject.parseObject(vo.toJSONString(), StoreVo.class);
        return ApiResult.ok(storeVo.getPolicy());
    }
}
