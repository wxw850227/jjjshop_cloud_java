package net.jjjshop.shop.service.statistics.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.product.Product;
import net.jjjshop.common.param.HomeParam;
import net.jjjshop.common.util.UploadFileUtils;
import net.jjjshop.common.vo.product.ProductHomeRankingVo;
import net.jjjshop.framework.util.DateUtil;
import net.jjjshop.shop.mapper.statistics.ProductRankingMapper;
import net.jjjshop.shop.service.order.OrderProductService;
import net.jjjshop.shop.service.product.ProductService;
import net.jjjshop.shop.service.statistics.ProductRankingService;
import net.jjjshop.shop.vo.product.ProductVo;
import net.jjjshop.shop.vo.statistics.ProductRefundRankingVo;
import net.jjjshop.shop.vo.statistics.ProductSaleRankingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品统计数据 服务实现类
 * @author jjjshop
 * @since 2022-06-28
 */

@Slf4j
@Service
public class ProductRankingServiceImpl implements ProductRankingService {

    @Autowired
    private ProductRankingMapper productRankingMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private OrderProductService orderProductService;

    /**
     * 获取商品销售榜单
     * @param
     * @return
     */
    @Override
    public List<ProductSaleRankingVo> getSaleRanking() {
        List<ProductSaleRankingVo> result = productRankingMapper.getSaleRanking();
        //去重
        result = result.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ProductSaleRankingVo::getProductId))), ArrayList::new));
        result.stream().forEach(e -> {
            e.setImagePath(uploadFileUtils.getImagePathByProductId(e.getProductId()));
            e.setViewTimes(productService.getById(e.getProductId()).getViewTimes());
        });
        return result;
    }

    /**
     * 获取商品浏览榜单
     * @param
     * @return
     */
    @Override
    public List<ProductVo> getViewsRanking() {
        List<Product> list = productService.list(new LambdaQueryWrapper<Product>()
                .gt(Product::getViewTimes, 0)
                .orderByDesc(Product::getViewTimes)
                .last("LIMIT 0,10"));
        List<ProductVo> result = list.stream().map(e -> {
            ProductVo vo = new ProductVo();
            BeanUtils.copyProperties(e, vo);
            vo.setImagePath(uploadFileUtils.getImagePathByProductId(vo.getProductId()));
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 获取商品退款榜单
     * @param
     * @return
     */
    @Override
    public List<ProductRefundRankingVo> getRefundRanking() {
        List<ProductRefundRankingVo> result = productRankingMapper.getRefundRanking();
        result.stream().forEach(e -> {
            e.setImagePath(uploadFileUtils.getImagePathByProductId(e.getProductId()));
            //名称已经有了
            //e.setProductName(productService.getById(e.getProductId()).getProductName());
        });
        return result;
    }

    /**
     * 获取商品总数
     * @param
     * @return
     */
    @Override
    public Long getProductTotal() {
        return productService.count(new LambdaQueryWrapper<Product>().eq(Product::getIsDelete, 0));
    }

    /**
     * 获取商品库存总数
     * @param
     * @return
     */
    @Override
    public Long getProductStockTotal() {
        return productService.count(new LambdaQueryWrapper<Product>().eq(Product::getIsDelete, 0).lt(Product::getProductStock, 20));
    }

    @Override
    public Long getProductTotalByDate(Date date) {
        return productService.count(new LambdaQueryWrapper<Product>()
                .between(Product::getCreateTime, DateUtil.getDateStart(date), DateUtil.getDateEnd(date))
                .eq(Product::getIsDelete, 0));
    }

    @Override
    public List<ProductHomeRankingVo> getSaleTimeRanking(HomeParam param) {
        List<ProductHomeRankingVo> result = productRankingMapper.getSaleTimeRanking(param);
        return result;
    }


}
