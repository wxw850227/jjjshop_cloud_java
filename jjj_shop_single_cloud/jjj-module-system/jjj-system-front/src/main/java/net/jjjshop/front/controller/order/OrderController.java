

package net.jjjshop.front.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.param.order.OrderBuyParam;
import net.jjjshop.front.service.product.ProductService;
import net.jjjshop.front.service.user.UserCartService;
import net.jjjshop.front.util.order.MasterOrderSettledUtils;
import net.jjjshop.front.vo.product.ProductBuyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "front-订单")
@Scope("prototype")
@RestController
@RequestMapping("/front/order/order")
public class OrderController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MasterOrderSettledUtils masterOrderSettledUtils;
    @Autowired
    private UserCartService userCartService;

    @RequestMapping(value = "/toBuy", method = RequestMethod.POST)
    @OperationLog(name = "toBuy")
    @Operation(summary = "toBuy")
    public ApiResult<Map<String, Object>> toBuy(@RequestBody OrderBuyParam orderBuyParam){
        User user = this.getUser(true);
        List<ProductBuyVo> productList = productService.getOrderProductListByNow(orderBuyParam);
        Map<String, Object> result = masterOrderSettledUtils.settlement(user, productList, orderBuyParam);
        result.put("productList", productList);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @OperationLog(name = "buy")
    @Operation(summary = "buy")
    public ApiResult<Integer> buy(@RequestBody OrderBuyParam orderBuyParam){
        User user = this.getUser(true);
        List<ProductBuyVo> productList = productService.getOrderProductListByNow(orderBuyParam);
        Integer orderId = masterOrderSettledUtils.createOrder(user, productList, orderBuyParam);
        return ApiResult.ok(orderId);
    }

    @RequestMapping(value = "/toCart", method = RequestMethod.POST)
    @OperationLog(name = "toCart")
    @Operation(summary = "toCart")
    public ApiResult<Map<String, Object>> toCart(@RequestBody OrderBuyParam orderBuyParam){
        User user = this.getUser(true);
        List<ProductBuyVo> productList = productService.getOrderProductListByCart(user, orderBuyParam);
        Map<String, Object> result = masterOrderSettledUtils.settlement(user, productList, orderBuyParam);
        result.put("productList", productList);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @OperationLog(name = "cart")
    @Operation(summary = "cart")
    public ApiResult<Integer> cart(@RequestBody OrderBuyParam orderBuyParam){
        User user = this.getUser(true);
        List<ProductBuyVo> productList = productService.getOrderProductListByCart(user, orderBuyParam);
        Integer orderId = masterOrderSettledUtils.createOrder(user, productList, orderBuyParam);
        // 移出购物车中已下单的商品
        userCartService.clearAll(orderBuyParam.getCartIds());
        return ApiResult.ok(orderId);
    }

}
