package net.jjjshop.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import net.jjjshop.config.properties.SwaggerProperties;
import org.apache.commons.collections4.CollectionUtils;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger3Config implements WebMvcConfigurer {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public GroupedOpenApi swaggerOpenApi() {
        return GroupedOpenApi.builder()
                .group("default")
                //扫描的基本包
                .packagesToScan(swaggerProperties.getBasePackage())
                // 剔除以下几个包路径的接口生成文档
//                .packagesToExclude("net.jjjshop.monitor")
                // 加了Operation注解的方法，才生成接口文档
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }



    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(swaggerProperties.getTitle())
                        .version(swaggerProperties.getVersion())
                        .contact(new Contact()
                                .name(swaggerProperties.getContactName())
                                .url(swaggerProperties.getContactUrl())
                                .email(swaggerProperties.getContactEmail()))
                        .description(swaggerProperties.getDescription())
                        .termsOfService("NO terms of service")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }

}
