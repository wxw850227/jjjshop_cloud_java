package net.jjjshop.shop.vo.statistics;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.OrderProduct;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "商品信息排行VO")
public class ProductSaleRankingVo extends OrderProduct {

    private static final long serialVersionUID = 1L;

    @Schema(description = "总销售额")
    private BigDecimal salesVolume;

    @Schema(description = "商品图片")
    private String imagePath;

    @Schema(description = "浏览次数")
    private Integer viewTimes;

}
