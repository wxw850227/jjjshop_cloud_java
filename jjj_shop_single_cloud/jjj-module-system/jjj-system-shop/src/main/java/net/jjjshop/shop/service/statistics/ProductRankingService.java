package net.jjjshop.shop.service.statistics;

import net.jjjshop.common.param.HomeParam;
import net.jjjshop.common.vo.product.ProductHomeRankingVo;
import net.jjjshop.shop.vo.product.ProductVo;
import net.jjjshop.shop.vo.statistics.ProductRefundRankingVo;
import net.jjjshop.shop.vo.statistics.ProductSaleRankingVo;

import java.util.Date;
import java.util.List;

/**
 * 商品统计数据 服务类
 * @author jjjshop
 * @since 2022-06-28
 */

public interface ProductRankingService {

    /**
     * 获取商品销售榜单
     * @param
     * @return
     */
    List<ProductSaleRankingVo> getSaleRanking();

    /**
     * 获取商品浏览榜单
     * @param
     * @return
     */
    List<ProductVo> getViewsRanking();

    /**
     * 获取商品退款榜单
     * @param
     * @return
     */
    List<ProductRefundRankingVo> getRefundRanking();

    /**
     * 获取商品总数
     * @param
     * @return
     */
    Long getProductTotal();

    /**
     * 获取商品库存总数
     * @param
     * @return
     */
    Long getProductStockTotal();

    Long getProductTotalByDate(Date today);

    List<ProductHomeRankingVo> getSaleTimeRanking(HomeParam param);
}
