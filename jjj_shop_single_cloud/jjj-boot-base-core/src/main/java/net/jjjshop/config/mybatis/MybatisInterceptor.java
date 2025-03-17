package net.jjjshop.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.core.bean.RequestDetail;
import net.jjjshop.framework.core.util.RequestDetailThreadLocal;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.shiro.SecurityUtils;
import net.jjjshop.common.constant.TenantConstant;
import net.jjjshop.common.util.oConvertUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * mybatis拦截器
 *
 * @Author jjjshop
 * @Date 2019-01-19
 */
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		log.debug("------sqlId------" + sqlId);
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		log.debug("------sqlCommandType------" + sqlCommandType);

		if (parameter == null) {
			return invocation.proceed();
		}
		if (SqlCommandType.INSERT == sqlCommandType) {
			Field[] fields = oConvertUtils.getAllFields(parameter);
			for (Field field : fields) {
				log.debug("------field.name------" + field.getName());
				try {
					//------------------------------------------------------------------------------------------------
					//注入租户ID（是否开启系统管理模块的多租户数据隔离【SAAS多租户模式】）
					if(MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL) {
						if (TenantConstant.TENANT_ID.equals(field.getName())) {
							field.setAccessible(true);
							Object localTenantId = field.get(parameter);
							field.setAccessible(false);
							if (localTenantId == null) {
								field.setAccessible(true);
								RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
								// 0为不带租户id
								if (requestDetail == null) {
									return invocation.proceed();
								}
								String tenantId = requestDetail.getTenantId().toString();

								if (field.getType().equals(String.class)) {
									// 字段类型为String
									field.set(parameter, tenantId);
								} else {
									// 字段类型不是String
									field.set(parameter, oConvertUtils.getInt(tenantId, 0));
								}
								field.setAccessible(false);
							}
						}
					}
					//------------------------------------------------------------------------------------------------

				} catch (Exception e) {
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
