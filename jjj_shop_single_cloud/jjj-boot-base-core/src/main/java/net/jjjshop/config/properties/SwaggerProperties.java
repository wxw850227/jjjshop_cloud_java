

package net.jjjshop.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Swagger配置属性
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-jjj.swagger")
public class SwaggerProperties {

    /**
     * 扫描的基本包
     */
    @Value("${spring-boot-jjj.swagger.base.package}")
    private String basePackage;

    /**
     * 联系人邮箱
     */
    @Value("${spring-boot-jjj.swagger.contact.email}")
    private String contactEmail;

    /**
     * 联系人名称
     */
    @Value("${spring-boot-jjj.swagger.contact.name}")
    private String contactName;

    /**
     * 联系人网址
     */
    @Value("${spring-boot-jjj.swagger.contact.url}")
    private String contactUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 网址
     */
    private String url;

    /**
     * 版本
     */
    private String version;
}
