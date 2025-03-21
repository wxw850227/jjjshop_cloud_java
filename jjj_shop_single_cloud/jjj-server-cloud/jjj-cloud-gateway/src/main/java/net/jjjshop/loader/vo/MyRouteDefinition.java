package net.jjjshop.loader.vo;

import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 自定义RouteDefinition
 * @author jjjshop
 */
public class MyRouteDefinition extends RouteDefinition {
    /**
     * 路由状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
