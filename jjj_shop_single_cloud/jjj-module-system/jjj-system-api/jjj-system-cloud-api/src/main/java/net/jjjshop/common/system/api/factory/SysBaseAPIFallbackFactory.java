package net.jjjshop.common.system.api.factory;

import net.jjjshop.common.system.api.fallback.SysBaseAPIFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import net.jjjshop.common.system.api.ISysBaseAPI;
import org.springframework.stereotype.Component;

/**
 * @Description: SysBaseAPIFallbackFactory
 * @author: jjjshop
 */
@Component
public class SysBaseAPIFallbackFactory implements FallbackFactory<ISysBaseAPI> {

    @Override
    public ISysBaseAPI create(Throwable throwable) {
        SysBaseAPIFallback fallback = new SysBaseAPIFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
