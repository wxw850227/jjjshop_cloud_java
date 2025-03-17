package net.jjjshop.common.system.api;

import net.jjjshop.common.api.CommonAPI;
import net.jjjshop.common.constant.ServiceNameConstants;
import net.jjjshop.common.system.api.factory.SysBaseAPIFallbackFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author: jjjshop
 */
@Component
@FeignClient(contextId = "sysBaseRemoteApi", value = ServiceNameConstants.SERVICE_SYSTEM, fallbackFactory = SysBaseAPIFallbackFactory.class)
@ConditionalOnMissingClass("net.jjjshop.shop.system.service.impl.SysBaseApiImpl")
public interface ISysBaseAPI extends CommonAPI {



}
