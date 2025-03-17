

package net.jjjshop.front.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.service.user.UserCartService;
import net.jjjshop.front.vo.product.ProductCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "front-购物车")
@RestController
@RequestMapping("/front/order/cart")
public class CartController extends BaseController {
    @Autowired
    private UserCartService userCartService;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<List<ProductCartVo>> lists(){
        User user = this.getUser(true);
        return ApiResult.ok(userCartService.getAll(user));
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(Integer productId, String specSkuId, Integer totalNum){
        User user = this.getUser(true);
        if(userCartService.add(user, productId, specSkuId, totalNum)){
            Integer cartTotalNum = userCartService.getTotalCartNum(user);
            return ApiResult.ok(""+cartTotalNum, "加入购物车成功");
        }else{
            return ApiResult.fail("加入购物车失败");
        }
    }

    @RequestMapping(value = "/sub", method = RequestMethod.POST)
    @OperationLog(name = "sub")
    @Operation(summary = "sub")
    public ApiResult<String> sub(Integer productId, String specSkuId){
        User user = this.getUser(true);
        if(userCartService.sub(user, productId, specSkuId)){
            return ApiResult.ok(null, "移除购物车成功");
        }else{
            return ApiResult.fail("移除购物车失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer[] cartIds){
        User user = this.getUser(true);
        if(userCartService.delete(user, cartIds)){
            return ApiResult.ok(null, "删除购物车商品成功");
        }else{
            return ApiResult.fail("删除购物车商品失败");
        }
    }
}
