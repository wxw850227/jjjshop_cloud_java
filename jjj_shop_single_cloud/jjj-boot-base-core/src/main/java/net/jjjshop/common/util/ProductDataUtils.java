package net.jjjshop.common.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.product.Product;
import net.jjjshop.common.service.product.ProductService;
import net.jjjshop.common.vo.product.ProductVo;
import net.jjjshop.framework.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductDataUtils {

    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    /**
     * 获取商品浏览榜单
     * @param
     * @return
     */
    public List<ProductVo> getViewsRanking() {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Product::getViewTimes, 0);
        wrapper.orderByDesc(Product::getViewTimes);
        wrapper.last("LIMIT 0,10");
        List<Product> list = productService.list(wrapper);
        List<ProductVo> result = list.stream().map(e -> {
            ProductVo vo = new ProductVo();
            BeanUtils.copyProperties(e, vo);
            vo.setImagePath(uploadFileUtils.getImagePathByProductId(vo.getProductId()));
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 获取商品总数
     * @param
     * @return
     */
    public Long getProductTotal(String type, Date date) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getIsDelete, 0);
        wrapper.eq(Product::getIsDelete, 0);
        //待审核
        if (type.equalsIgnoreCase("audit")) {
            //排除回收站 产品状态(10上架 20仓库中 30回收站)
            wrapper.ne(Product::getProductStatus, 30);
        }
        if(date != null){
            wrapper.between(Product::getCreateTime, DateUtil.getDateStart(date), DateUtil.getDateEnd(date));
        }
        return productService.count(wrapper);
    }

    /**
     * 获取商品库存总数
     * @param
     * @return
     */
    public Long getProductStockTotal() {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getIsDelete, 0)
                .ge(Product::getProductStock, 1).le(Product::getProductStock, 10)
                .eq(Product::getProductStatus, 10);
        return productService.count(wrapper);
    }

}
