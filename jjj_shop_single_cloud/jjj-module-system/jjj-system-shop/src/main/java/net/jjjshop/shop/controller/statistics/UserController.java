package net.jjjshop.shop.controller.statistics;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.statistics.RankingParam;
import net.jjjshop.shop.service.statistics.OrderRankingService;
import net.jjjshop.shop.service.statistics.ProductRankingService;
import net.jjjshop.shop.service.statistics.UserRankingService;
import net.jjjshop.shop.vo.user.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "shop-会员统计")
@RestController
@RequestMapping("/shop/statistics/user")
public class UserController {
    @Autowired
    private ProductRankingService productRankingService;

    @Autowired
    private OrderRankingService orderRankingService;

    @Autowired
    private UserRankingService userRankingService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/statistics/user/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String,Object>> index() throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        JSONObject user = userRankingService.getData();
        List<UserVo> payRanking = userRankingService.getUserRanking("pay");
        map.put("user",user);
        map.put("payRanking",payRanking);
        return ApiResult.ok(map);
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    @OperationLog(name = "newuser")
    @Operation(summary = "newuser")
    public ApiResult<Map<String,Object>> newuser(@Validated @RequestBody RankingParam RankingParam) throws Exception{
        return ApiResult.ok(userRankingService.getNewUserByDate(RankingParam.getStartDate(), RankingParam.getEndDate()));
    }

    @RequestMapping(value = "/scale", method = RequestMethod.POST)
    @OperationLog(name = "scale")
    @Operation(summary = "scale")
    public ApiResult<Map<String,Object>> scale(Integer day) throws Exception{
        return  ApiResult.ok(userRankingService.getPayScaleData(day));
    }

    @RequestMapping(value = "/payuser", method = RequestMethod.POST)
    @OperationLog(name = "payuser")
    @Operation(summary = "payuser")
    public ApiResult<Map<String,Object>> payuser(@Validated @RequestBody RankingParam RankingParam) throws Exception{
        return  ApiResult.ok(userRankingService.getPayUserByDate(RankingParam.getStartDate(), RankingParam.getEndDate()));
    }

}
