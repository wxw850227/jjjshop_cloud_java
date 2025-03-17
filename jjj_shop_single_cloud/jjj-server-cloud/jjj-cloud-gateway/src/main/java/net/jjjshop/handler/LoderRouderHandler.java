package net.jjjshop.handler;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.base.BaseMap;
import org.jeecg.common.constant.GlobalConstants;
import org.jeecg.common.modules.redis.listener.JeecgRedisListener;
import net.jjjshop.loader.DynamicRouteLoader;
import org.springframework.stereotype.Component;

/**
 * 路由刷新监听（实现方式：redis监听handler）
 * @Author  jjjshop
 * @date: 2022/4/21 10:55
 */
@Slf4j
@Component(GlobalConstants.LODER_ROUDER_HANDLER)
public class LoderRouderHandler implements JeecgRedisListener {

    @Resource
    private DynamicRouteLoader dynamicRouteLoader;


    @Override
    public void onMessage(BaseMap message) {
        dynamicRouteLoader.refresh(message);
    }

}
