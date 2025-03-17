package net.jjjshop.shop.service.order;

import com.alibaba.fastjson.JSONObject;
import net.jjjshop.common.entity.order.Order;
import net.jjjshop.framework.common.service.BaseService;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.shop.param.order.*;
import net.jjjshop.shop.vo.order.OrderVo;

import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * 订单记录表 服务类
 * @author jjjshop
 * @since 2022-07-04
 */
public interface OrderService extends BaseService<Order> {
    /**
     * 订单列表
     * @param orderPageParam
     * @return
     */
    Paging<OrderVo> getList(OrderPageParam orderPageParam);

    /**
     * 单个订单信息
     * @param orderId
     * @return
     */
    OrderVo detail(Integer orderId);

    /**
     * 订单发货
     * @param orderParam
     * @return
     */
    Boolean delivery(OrderParam orderParam);

    /**
     * 审核：用户取消订单
     * @param orderParam
     * @return
     */
    Boolean confirmCancel(OrderParam orderParam);

    /**
     * 通过订单状态查询订单数量
     * @return order
     */
    Long getCount(String dataType);

    /**
     * 获取订单统计数据
     * @param startDate
     * @param endDate
     * @param type
     * @return
     */
    BigDecimal getOrderData(String startDate, String endDate, String type) ;

    /**
     * 获取待处理订单总数
     * @param
     * @return
     */
    Long getReviewOrderTotal();

    /**
     * 获取提现订单总量
     * @param
     * @return
     */
    Long getCardOrderTotal();

    /**
     * 导出订单
     * @param orderPageParam
     * @param httpServletResponse
     * @return
     */
    void exportList(OrderPageParam orderPageParam, HttpServletResponse httpServletResponse) throws Exception;

    /**
     * 确认核销订单
     * @param orderExtractParam
     * @return
     */
    Boolean verificationOrder(OrderExtractParam orderExtractParam);

    /**
     * 取消订单
     * @param orderCancelParam
     * @return
     */
    Boolean orderCancel(OrderCancelParam orderCancelParam);

    /**
     * 获取所有运送方式
     * @param
     * @return
     */
    List<JSONObject> getDeliveryList();

    /**
     * 虚拟商品发货
     * @param orderVirtualParam
     * @return
     */
    boolean virtual(OrderVirtualParam orderVirtualParam);

    //修改订单价格
    boolean updatePrice(OrderPriceParam orderPriceParam);
}
