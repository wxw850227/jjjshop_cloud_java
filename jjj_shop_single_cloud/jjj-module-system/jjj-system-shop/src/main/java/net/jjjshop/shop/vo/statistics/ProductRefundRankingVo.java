package net.jjjshop.shop.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.OrderRefund;

@Data
@Accessors(chain = true)
@Schema(description = "商品退货信息排行VO")
public class ProductRefundRankingVo extends OrderRefund {

    private static final long serialVersionUID = 1L;

    @Schema(description = "总销售额")
    private Long refundCount;

    @Schema(description = "商品图片")
    private String imagePath;

    @Schema(description = "商品Id")
    private Integer productId;

    @Schema(description = "商品名称")
    private String productName;
}
