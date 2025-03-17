package net.jjjshop.config.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import jakarta.annotation.PostConstruct;
import net.jjjshop.common.constant.TenantConstant;
import net.jjjshop.config.JjjNameGenerator;
import net.jjjshop.config.properties.MybatisPrefixProperties;
import net.jjjshop.framework.common.exception.BusinessException;
import net.jjjshop.framework.core.bean.RequestDetail;
import net.jjjshop.framework.core.util.RequestDetailThreadLocal;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @Author  jjjshop
 *
 */
@Configuration
public class MybatisPlusSaasConfig {

    @Autowired
    private MybatisPrefixProperties mybatisPrefixProperties;

    /**
     * 是否开启系统模块的租户隔离
     * <p>
     * 实现功能
     * 4.通过拦截器MybatisInterceptor实现，增删改查数据 自动注入租户ID
     */
    public static final Boolean OPEN_SYSTEM_TENANT_CONTROL = true;

    /**
     * 哪些表不需要做多租户
     */
    public static final List<String> IGNORE_TENANT_TABLE = new ArrayList<String>();

    //不需要租户隔离的表,确保在所有依赖注入完成后执行初始化逻辑
    @PostConstruct
    public void initIgnoreTenantTables() {
        if (MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL) {
            String tablePrefix = mybatisPrefixProperties.getDbConfig().getTablePrefix();
            if (tablePrefix != null) {
                IGNORE_TENANT_TABLE.add(tablePrefix + "region");
                IGNORE_TENANT_TABLE.add(tablePrefix + "message");
                IGNORE_TENANT_TABLE.add(tablePrefix + "message_field");
                IGNORE_TENANT_TABLE.add(tablePrefix + "system_version");
                IGNORE_TENANT_TABLE.add(tablePrefix + "shop_access");
                IGNORE_TENANT_TABLE.add(tablePrefix + "plus_category");
            }else {
                throw new BusinessException("请先配置表前缀tablePrefix");
            }
        }
    }


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
                // 0为不带租户id
                if (requestDetail == null) {
                    return new LongValue(0);
                }
                return new LongValue(requestDetail.getTenantId());
            }

            @Override
            public String getTenantIdColumn(){
                return TenantConstant.TENANT_ID_TABLE;
            }

            // 返回 true 表示不走租户逻辑
            @Override
            public boolean ignoreTable(String tableName) {
                //是否开启系统模块的租户隔离
                if (!MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL) {
                    //如果关闭,全部返回true
                    return true;
                }
                RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
                // 0为不带租户id
                if (requestDetail == null || requestDetail.getTenantId() == 0) {
                    return true;
                }
                return IGNORE_TENANT_TABLE.contains(tableName);
            }
        }));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}
