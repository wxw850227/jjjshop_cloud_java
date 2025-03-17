

package net.jjjshop.framework.util;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据lambda表达式获取数据库列名
 **/
public class LambdaColumn<T> {

    private static Map<String, Map<String, String>> ENTITY_COLUMN_MAP = new ConcurrentHashMap<>();

    public static <T> LambdaColumn<T> lambda() {
        return new LambdaColumn<>();
    }


    /**
     * 从mybatisplus的TableInfo类中获取列名map信息
     *
     * @param cls
     * @return
     */
    private Map<String, String> getPropertyColumnMap(Class<?> cls) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
        if (CollectionUtils.isEmpty(tableFieldInfos)) {
            return null;
        }

        Map<String, String> map = new ConcurrentHashMap<>();
        String keyProperty = tableInfo.getKeyProperty();
        String keyColumn = tableInfo.getKeyColumn();
        map.put(keyProperty, keyColumn);

        for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
            String column = tableFieldInfo.getColumn();
            String property = tableFieldInfo.getProperty();
            map.put(property, column);
        }
        return map;
    }

}
