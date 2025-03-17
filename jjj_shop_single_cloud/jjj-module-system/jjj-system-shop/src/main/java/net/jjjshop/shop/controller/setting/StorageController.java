

package net.jjjshop.shop.controller.setting;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.settings.vo.StorageVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-上传设置")
@RestController
@RequestMapping("/shop/setting/storage")
public class StorageController {

    @Autowired
    private SettingUtils settingUtils;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @RequiresPermissions("/setting/storage/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<StorageVo> index() throws Exception{
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORAGE.getKey(), null);
        StorageVo storageVo = JSONObject.toJavaObject(vo, StorageVo.class);
        return ApiResult.ok(storageVo);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/setting/storage/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<String> index(@RequestBody JSONObject jsonData) throws Exception{
        settingUtils.saveSetting(SettingEnum.STORAGE.getKey(), jsonData);
        return ApiResult.ok("保存成功");
    }
}
