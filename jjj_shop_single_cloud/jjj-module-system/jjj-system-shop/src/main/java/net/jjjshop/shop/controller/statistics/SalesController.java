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
import net.jjjshop.shop.vo.product.ProductVo;
import net.jjjshop.shop.vo.statistics.ProductRefundRankingVo;
import net.jjjshop.shop.vo.statistics.ProductSaleRankingVo;
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
@Tag(name = "shop-销售统计")
@RestController
@RequestMapping("/shop/statistics/sales")
public class SalesController {

    @Autowired
    private ProductRankingService productRankingService;

    @Autowired
    private OrderRankingService orderRankingService;

    @Autowired
    private UserRankingService userRankingService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/statistics/sales/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String,Object>> index() throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        JSONObject order = orderRankingService.getData();
        JSONObject product = orderRankingService.getProductData();
        List<ProductSaleRankingVo> saleRanking = productRankingService.getSaleRanking();
        List<ProductVo> viewsRanking = productRankingService.getViewsRanking();
        List<ProductRefundRankingVo> refundRanking = productRankingService.getRefundRanking();
        map.put("order",order);
        map.put("product",product);
        map.put("saleRanking",saleRanking);
        map.put("viewsRanking",viewsRanking);
        map.put("refundRanking",refundRanking);
        return ApiResult.ok(map);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @OperationLog(name = "order")
    @Operation(summary = "order")
    public ApiResult<Map<String,Object>> order(@Validated @RequestBody RankingParam rankingParam) throws Exception{
        return ApiResult.ok(orderRankingService.getOrderDataByDate(rankingParam));
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @OperationLog(name = "product")
    @Operation(summary = "product")
    public ApiResult<Map<String,Object>> product(@Validated @RequestBody RankingParam RankingParam) throws Exception{
        return  ApiResult.ok(orderRankingService.getProductDataByDate(RankingParam.getStartDate(), RankingParam.getEndDate()));
    }

}
