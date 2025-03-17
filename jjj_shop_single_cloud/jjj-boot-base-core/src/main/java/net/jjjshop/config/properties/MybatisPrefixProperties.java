

package net.jjjshop.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * mybatis-plus属性配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "mybatis-plus.global-config")
public class MybatisPrefixProperties {

    /**
     * 自定义权限拦截器
     */
    @NestedConfigurationProperty
    private MybatisDbConfig dbConfig = new MybatisDbConfig();

    @Data
    public static class MybatisDbConfig {

        /**
         * 表前缀
         */
        private String tablePrefix;

    }



}
