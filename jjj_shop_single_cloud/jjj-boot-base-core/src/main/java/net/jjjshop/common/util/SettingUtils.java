package net.jjjshop.common.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.Setting;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.mapper.settings.SettingMapper;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.config.constant.CommonRedisKey;
import net.jjjshop.framework.core.util.RequestDetailThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SettingUtils {

    private static final String backPackage = "net.jjjshop.common.settings.vo.";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 通过key查找设置信息
     *
     * @param key
     * @return
     * @throws Exception
     */
    public JSONObject getSetting(String key, Long tenantId) {
        if (tenantId == null) {
            tenantId = RequestDetailThreadLocal.getRequestDetail().getTenantId();
        }
        String cacheKey = String.format(CommonRedisKey.SETTING_DATA, key, tenantId);
        // 如果缓存中存在,则返回
        Object object = redisTemplate.opsForValue().get(cacheKey);
        if (object != null) {
            //return (JSONObject)object;
        }
        // 先从数据库查询，如果没有，则取默认数据
        Setting setting = settingMapper.selectOne(new LambdaQueryWrapper<Setting>()
                .eq(Setting::getSetKey, key).eq(Setting::getTenantId, tenantId));
        JSONObject result = null;
        try {
            if (setting != null) {
                result = JSONObject.parseObject(setting.getSetValue());
            } else {
                Class cla = Class.forName(backPackage + SettingEnum.getClassNameByKey(key));
                Object vo = cla.newInstance();
                result = (JSONObject) JSONObject.toJSON(vo);
            }
            // 存入缓存
            redisTemplate.opsForValue().set(cacheKey, result);
        } catch (Exception e) {
            log.info("获取设置异常:", e.getMessage());
        }
        return result;
    }

    /**
     * 保存
     *
     * @param key
     * @param jsonData
     * @return
     */
    public Boolean saveSetting(String key, JSONObject jsonData) {
        Setting setting = settingMapper.selectOne(new LambdaQueryWrapper<Setting>()
                .eq(Setting::getSetKey, key));
        Setting bean = new Setting();
        // 删除缓存
        redisTemplate.delete(String.format(CommonRedisKey.SETTING_DATA, key, RequestDetailThreadLocal.getRequestDetail().getTenantId()));
        if (setting == null) {
            // 新增
            bean.setSetKey(key);
            bean.setDescription(SettingEnum.getDescriptionByKey(key));
            bean.setSetValue(jsonData.toJSONString());
            return SqlHelper.retBool(settingMapper.insert(bean));
        } else {
            // 修改
            bean.setSetKey(key);
            bean.setSetValue(jsonData.toJSONString());
            return SqlHelper.retBool(settingMapper.updateById(bean));
        }
    }

    //生成图形验证码并保存到redis,有效期5分钟
    public LoginParam generateValidateCode() {
        LoginParam param = new LoginParam();
        //通过工具类生成图片验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        //验证码的值
        String codeValue = circleCaptcha.getCode();
        //将图片进行base64编码，并返回
        String imageBase64 = circleCaptcha.getImageBase64();
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        String cacheKey = String.format(CommonRedisKey.SETTING_DATA, "code", key);
        //将验证码的值存入redis并设置5分钟过期
        redisTemplate.opsForValue().set(cacheKey, codeValue, 5, TimeUnit.MINUTES);
        param.setCodeKey(cacheKey);
        param.setCodeImage("data:image/png;base64," + imageBase64);
        return param;
    }
}
