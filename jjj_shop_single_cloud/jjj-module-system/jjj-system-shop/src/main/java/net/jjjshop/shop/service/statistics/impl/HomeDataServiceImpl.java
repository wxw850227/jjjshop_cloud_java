package net.jjjshop.shop.service.statistics.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.param.HomeParam;
import net.jjjshop.common.param.HomeRankingParam;
import net.jjjshop.common.util.ProductDataUtils;
import net.jjjshop.common.util.UserDataUtils;
import net.jjjshop.framework.util.ShopLoginUtil;
import net.jjjshop.shop.param.statistics.RankingParam;
import net.jjjshop.shop.service.order.OrderRefundService;
import net.jjjshop.shop.service.order.OrderService;
import net.jjjshop.shop.service.product.ProductCommentService;
import net.jjjshop.shop.service.statistics.HomeDataService;
import net.jjjshop.shop.service.statistics.OrderRankingService;
import net.jjjshop.shop.service.statistics.ProductRankingService;
import net.jjjshop.shop.service.statistics.UserRankingService;
import net.jjjshop.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 首页统计数据 服务实现类
 * @author jjjshop
 * @since 2022-06-28
 */

@Slf4j
@Service
public class HomeDataServiceImpl implements HomeDataService {

    @Autowired
    private OrderRankingService orderRankingService;
    @Autowired
    private ProductRankingService productRankingService;
    @Autowired
    private UserRankingService userRankingService;
    @Autowired
    private ProductCommentService productCommentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private ProductDataUtils productDataUtils;
    @Autowired
    private UserDataUtils userDataUtils;


    private JSONObject getWaitData() {
        JSONObject result = new JSONObject();
        // --订单待办--
        JSONObject order = new JSONObject();
        //待处理订单
        order.put("disposal", orderService.getReviewOrderTotal());
        //获取待审核售后订单总量
        order.put("refund", orderRefundService.getRefundTotal());
        //平台售后订单
        order.put("card_count", orderService.getCardOrderTotal());
        result.put("order", order);

        // --审核--
        JSONObject review = new JSONObject();
        //评论
        review.put("comment", productCommentService.getReviewCommentTotal());
        result.put("review", review);

        // --商品待办--
        JSONObject stock = new JSONObject();
        //库存
        stock.put("product", productDataUtils.getProductStockTotal());
        result.put("stock", stock);
        return result;
    }

    private JSONObject getTopData() throws ParseException{
        JSONObject result = new JSONObject();
        Date today = new Date();
        Date yesterday = DateUtil.offsetDay(today, -1);
        String t = net.jjjshop.framework.util.DateUtil.getDateString(today);
        String y = net.jjjshop.framework.util.DateUtil.getDateString(yesterday);
        // 商品总量
        result.put("product_total", productRankingService.getProductTotal());
        Long product_today = productRankingService.getProductTotalByDate(today);
        Long product_yesterday = productRankingService.getProductTotalByDate(yesterday);
        BigDecimal product_rate = product_yesterday >0 ? BigDecimal.valueOf(product_today - product_yesterday)
                .divide(BigDecimal.valueOf(product_yesterday), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) :
                BigDecimal.valueOf(product_today).multiply(BigDecimal.valueOf(100));
        result.put("product_today", product_today);
        result.put("product_yesterday", product_yesterday);
        result.put("product_rate", product_rate);
        // 用户总量
        result.put("user_total", userRankingService.getUserTotal());
        Long user_today = userService.getUserData(t, null, "user_total");
        Long user_yesterday = userService.getUserData(y, null, "user_total");
        BigDecimal user_rate = user_yesterday >0 ? BigDecimal.valueOf(user_today - user_yesterday)
                .divide(BigDecimal.valueOf(user_yesterday), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) :
                BigDecimal.valueOf(user_today).multiply(BigDecimal.valueOf(100));
        result.put("user_today", user_today);
        result.put("user_yesterday", user_yesterday);
        result.put("user_rate", user_rate);
        // 订单总量
        result.put("order_total", Integer.parseInt(orderRankingService.getOrderTotal().toString()));
        // 支付订单数
        Integer order_today = Integer.parseInt(orderService.getOrderData(t, null, "order_total").toString());
        Integer order_yesterday = Integer.parseInt(orderService.getOrderData(y, null, "order_total").toString());
        BigDecimal order_rate = order_yesterday >0 ? BigDecimal.valueOf(order_today - order_yesterday)
                .divide(BigDecimal.valueOf(order_yesterday), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) :
                BigDecimal.valueOf(order_today).multiply(BigDecimal.valueOf(100));
        result.put("order_today", order_today);
        result.put("order_yesterday", order_yesterday);
        result.put("order_rate", order_rate);
        // 评价总量
        result.put("comment_total", productCommentService.getProductCommentTotal());
        Long comment_today = productCommentService.getSupplierTotalByDate(today);
        Long comment_yesterday = productCommentService.getSupplierTotalByDate(yesterday);
        BigDecimal comment_rate = comment_yesterday >0 ? BigDecimal.valueOf(comment_today - comment_yesterday)
                .divide(BigDecimal.valueOf(comment_yesterday), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) :
                BigDecimal.valueOf(comment_today).multiply(BigDecimal.valueOf(100));
        result.put("comment_today", comment_today);
        result.put("comment_yesterday", comment_yesterday);
        result.put("comment_rate", comment_rate);
        return result;
    }

    @Override
    public JSONObject getHomeData(HomeParam param) throws ParseException {
        JSONObject result = new JSONObject();
        result.put("top_data", this.getTopData());
        result.put("wait_data", this.getWaitData());
        param.setTenantId(ShopLoginUtil.getTenantId());
        //商品销售排行
        result.put("productRank", productRankingService.getSaleTimeRanking(param));
        //销售额概况
        result.put("saleData", this.getSaleData(param.getSaleTime()));
        //用户数据
        result.put("userData", this.getUserByDate(param.getUserTime()));
        //数据更新时间
        result.put("update_time", net.jjjshop.framework.util.DateUtil.getDateString(new Date()));
        return result;
    }

    /**
     * 通过时间段查询订单数据
     */
    private Map<String, Object> getUserByDate(Integer userTime) {
        HomeRankingParam rankingParam = new HomeRankingParam();
        Date end = new Date();
        Date start = this.getStartDate(userTime,end);
        rankingParam.setStartDate(net.jjjshop.framework.util.DateUtil.getDateString(start));
        rankingParam.setEndDate(net.jjjshop.framework.util.DateUtil.getDateString(end));
        rankingParam.setType("order");
        return userDataUtils.getUserByDate(rankingParam);
    }

    /**
     * 通过时间段查询订单数据
     */
    private Map<String, Object> getSaleData(Integer saleTime) {
        RankingParam rankingParam = new RankingParam();
        Date end = new Date();
        Date start = this.getStartDate(saleTime,end);
        rankingParam.setStartDate(net.jjjshop.framework.util.DateUtil.getDateString(start));
        rankingParam.setEndDate(net.jjjshop.framework.util.DateUtil.getDateString(end));
        rankingParam.setType("order");
        return orderRankingService.getOrderDataByDate(rankingParam);
    }

    private Date getStartDate(Integer type, Date end) {
        //默认一个星期
        Date start = DateUtil.offsetDay(end,-7);
        //1=最近7天,2=最近15天,3=最近30天
        if(type != null && type == 2){
            start = DateUtil.offsetDay(end,-15);
        }
        if(type != null && type == 3){
            start = DateUtil.offsetDay(end,-30);
        }
        return start;
    }
}
