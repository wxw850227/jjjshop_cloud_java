package net.jjjshop.common.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.common.param.HomeRankingParam;
import net.jjjshop.common.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class UserDataUtils {

    @Autowired
    private UserService userService;


    public Map<String, Object> getUserByDate(HomeRankingParam rankingParam) {
        Map<String, Object> map = new HashMap<>();
        Date startTime = DateUtil.parse(rankingParam.getStartDate());
        Date endTime = DateUtil.parse(rankingParam.getEndDate());
        //endTime加一天
        endTime = DateUtil.offsetDay(endTime,1);
        List<JSONObject> data = new ArrayList<>();
        List<String> days = new ArrayList<>();
        for (Date t = startTime; t.before(endTime); t = DateUtil.offsetDay(t,1)) {
            String day = DateUtil.format(t, "yyyy-MM-dd");
            String dayM = DateUtil.format(t, "MM-dd");
            //新增用户
            Long user_num = this.getUserData(day, null);
            //累计用户
            Long total_num = this.getUserData(null, day);

            JSONObject json = new JSONObject();
            json.put("day", day);
            json.put("user_num", user_num);
            json.put("total_num", total_num);
            days.add(dayM);
            data.add(json);
        }
        map.put("data", data);
        map.put("days", days);
        return map;
    }

    /**
     * 获取用户统计数据
     * @param startDate
     * @param endDate
     * @return
     */
    //获取订单统计数据
    public Long getUserData(String startDate, String endDate)  {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //开始查询时间不为空
        if (StringUtils.isNotEmpty(startDate)) {
            wrapper.ge(User::getCreateTime, DateUtil.parse(startDate + " 00:00:00"));
        }
        //结束查询时间不为空
        if (StringUtils.isNotEmpty(endDate)) {
            wrapper.le(User::getCreateTime, DateUtil.parse(endDate+" 23:59:59"));
        } else if (StringUtils.isNotEmpty(startDate)) {
            //如果结束查询时间为空,开始查询时间不为空，就默认设置时间查询区间为开始时间+1天
            wrapper.le(User::getCreateTime,DateUtil.parse(startDate+" 23:59:59"));
        }
        wrapper.eq(User::getIsDelete, 0);
        return userService.count(wrapper);
    }
}
