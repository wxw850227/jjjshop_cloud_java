

package net.jjjshop.generator.config.query;

import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;

/**
 * MySQL代码生成查询是否为空的列
 **/
public class SpringBootJjjMySqlQuery extends MySqlQuery {

    @Override
    public String[] fieldCustom() {
        return new String[]{"null", "default"};
    }

}
