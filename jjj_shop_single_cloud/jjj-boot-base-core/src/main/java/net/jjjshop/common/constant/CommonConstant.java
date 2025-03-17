package net.jjjshop.common.constant;

/**
 * @Description: 通用常量
 * @author: jjjshop
 */
public interface CommonConstant {


    String X_FORWARDED_SCHEME = "X-Forwarded-Scheme";


    /**
     * 微服务读取配置文件属性 服务地址
     */
    String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";

    /**未知的*/
    String UNKNOWN = "unknown";

    /**String 类型的空值*/
    String STRING_NULL = "null";
    /**
     * 排序类型：升序
     */
    String ORDER_TYPE_ASC = "ASC";
    /**
     * 排序类型：降序
     */
    String ORDER_TYPE_DESC = "DESC";
}
