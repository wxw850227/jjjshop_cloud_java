package net.jjjshop.common.util.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import net.jjjshop.common.entity.app.AppWx;
import net.jjjshop.common.service.app.AppWxService;
import net.jjjshop.framework.core.util.RequestDetailThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 维信小程序工具类
 */
@Configuration
public class AppWxUtils {

    @Lazy
    @Autowired
    private AppWxService appWxService;

    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        // 临时放入，支付会再次设置
        config.setAppid("jjjshop");
        config.setSecret("jjjshop");
        return config;
    }


    @Bean
    public WxMaService wxMaService(WxMaConfig maConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(maConfig);
        return service;
    }

    public WxMaConfig getConfig() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        Long tenantId = RequestDetailThreadLocal.getRequestDetail().getTenantId();
        AppWx appWx = appWxService.getById(tenantId);
        config.setAppid(appWx.getWxappId());
        config.setSecret(appWx.getWxappSecret());
        return config;
    }
}
