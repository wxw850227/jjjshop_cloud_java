package net.jjjshop.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.constant.DataBaseConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 通用工具
 * @author: jjjshop
 */
@Slf4j
public class CommonUtils {

    /**
     * 中文正则
     */
    private static Pattern ZHONGWEN_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 文件名 正则字符串
     * 文件名支持的字符串：字母数字中文.-_()（） 除此之外的字符将被删除
     */
    private static String FILE_NAME_REGEX = "[^A-Za-z\\.\\(\\)\\-（）\\_0-9\\u4e00-\\u9fa5]";


    /**
     * 判断文件名是否带盘符，重新处理
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName){
        //判断是否带有盘符信息
        // Check for Unix-style path
        int unixSep = fileName.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = fileName.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1)  {
            // Any sort of path separator found...
            fileName = fileName.substring(pos + 1);
        }
        //替换上传文件名字的特殊字符
        fileName = fileName.replace("=","").replace(",","").replace("&","")
                .replace("#", "").replace("“", "").replace("”", "");
        //替换上传文件名字中的空格
        fileName=fileName.replaceAll("\\s","");
        //update-beign-author:taoyan date:20220302 for: /issues/3381 online 在线表单 使用文件组件时，上传文件名中含%，下载异常
        fileName = fileName.replaceAll(FILE_NAME_REGEX, "");
        //update-end-author:taoyan date:20220302 for: /issues/3381 online 在线表单 使用文件组件时，上传文件名中含%，下载异常
        return fileName;
    }

    /**
     * java 判断字符串里是否包含中文字符
     * @param str
     * @return
     */
    public static boolean ifContainChinese(String str) {
        if(str.getBytes().length == str.length()){
            return false;
        }else{
            Matcher m = ZHONGWEN_PATTERN.matcher(str);
            if (m.find()) {
                return true;
            }
            return false;
        }
    }


    /** 当前系统数据库类型 */
    private static String DB_TYPE = "";
    private static DbType dbTypeEnum = null;

    /**
     * 全局获取平台数据库类型（作废了）
     * @return
     */
    @Deprecated
    public static String getDatabaseType() {
        if(oConvertUtils.isNotEmpty(DB_TYPE)){
            return DB_TYPE;
        }
        DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        try {
            return getDatabaseTypeByDataSource(dataSource);
        } catch (SQLException e) {
            //e.printStackTrace();
            log.warn(e.getMessage(),e);
            return "";
        }
    }

    /**
     * 全局获取平台数据库类型（对应mybaisPlus枚举）
     * @return
     */
    public static DbType getDatabaseTypeEnum() {
        if (oConvertUtils.isNotEmpty(dbTypeEnum)) {
            return dbTypeEnum;
        }
        try {
            DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
            dbTypeEnum = JdbcUtils.getDbType(dataSource.getConnection().getMetaData().getURL());
            return dbTypeEnum;
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取数据库类型
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private static String getDatabaseTypeByDataSource(DataSource dataSource) throws SQLException{
        if("".equals(DB_TYPE)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData md = connection.getMetaData();
                String dbType = md.getDatabaseProductName().toUpperCase();
                String sqlserver= "SQL SERVER";
                if(dbType.indexOf(DataBaseConstant.DB_TYPE_MYSQL)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MYSQL;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_ORACLE)>=0 ||dbType.indexOf(DataBaseConstant.DB_TYPE_DM)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_ORACLE;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_SQLSERVER)>=0||dbType.indexOf(sqlserver)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_SQLSERVER;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_POSTGRESQL)>=0 || dbType.indexOf(DataBaseConstant.DB_TYPE_KINGBASEES)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_POSTGRESQL;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_MARIADB)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MARIADB;
                }else {
                    log.error("数据库类型:[" + dbType + "]不识别!");
                    //throw new BusinessException("数据库类型:["+dbType+"]不识别!");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }finally {
                connection.close();
            }
        }
        return DB_TYPE;

    }

    /**
     * 递归合并 fastJSON 对象
     *
     * @param target  目标对象
     * @param sources 来源对象，允许多个，优先级从左到右，最右侧的优先级最高
     */
    public static JSONObject mergeJSON(JSONObject target, JSONObject... sources) {
        for (JSONObject source : sources) {
            CommonUtils.mergeJSON(target, source);
        }
        return target;
    }

    /**
     * 递归合并 fastJSON 对象
     *
     * @param target 目标对象
     * @param source 来源对象
     */
    public static JSONObject mergeJSON(JSONObject target, JSONObject source) {
        for (String key : source.keySet()) {
            Object sourceItem = source.get(key);
            // 是否是 JSONObject
            if (sourceItem instanceof Map) {
                // target中存在此key
                if (target.containsKey(key)) {
                    // 两个都是 JSONObject，继续合并
                    if (target.get(key) instanceof Map) {
                        CommonUtils.mergeJSON(target.getJSONObject(key), source.getJSONObject(key));
                        continue;
                    }
                }
            }
            // target不存在此key，或不是 JSONObject，则覆盖
            target.put(key, sourceItem);
        }
        return target;
    }

    /**
     * 将list集合以分割符的方式进行分割
     * @param list      String类型的集合文本
     * @param separator 分隔符
     * @return
     */
    public static String getSplitText(List<String> list, String separator) {
        if (null != list && list.size() > 0) {
            return StringUtils.join(list, separator);
        }
        return "";
    }

    /**
     * 通过table的条件SQL
     *
     * @param tableSql sys_user where name = '1212'
     * @return name = '1212'
     */
    public static String getFilterSqlByTableSql(String tableSql) {
        if(oConvertUtils.isEmpty(tableSql)){
            return null;
        }

        if (tableSql.toLowerCase().indexOf(DataBaseConstant.SQL_WHERE) > 0) {
            String[] arr = tableSql.split(" (?i)where ");
            if (arr != null && oConvertUtils.isNotEmpty(arr[1])) {
                return arr[1];
            }
        }
        return "";
    }

    /**
     * 通过table获取表名
     *
     * @param tableSql sys_user where name = '1212'
     * @return sys_user
     */
    public static String getTableNameByTableSql(String tableSql) {
        if(oConvertUtils.isEmpty(tableSql)){
            return null;
        }

        if (tableSql.toLowerCase().indexOf(DataBaseConstant.SQL_WHERE) > 0) {
            String[] arr = tableSql.split(" (?i)where ");
            return arr[0].trim();
        } else {
            return tableSql;
        }
    }

    /**
     * 判断两个数组是否存在交集
     * @param set1
     * @param arr2
     * @return
     */
    public static boolean hasIntersection(Set<String> set1, String[] arr2) {
        if (set1 == null) {
            return false;
        }

        if(set1.size()>0){
            for (String str : arr2) {
                if (set1.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 输出info日志，会捕获异常，防止因为日志问题导致程序异常
     *
     * @param msg
     * @param objects
     */
    public static void logInfo(String msg, Object... objects) {
        try {
            log.info(msg, objects);
        } catch (Exception e) {
            log.warn("{} —— {}", msg, e.getMessage());
        }
    }

}
