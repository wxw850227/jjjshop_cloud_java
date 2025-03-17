package net.jjjshop.common.vo.product;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "商品信息排行VO")
public class ProductHomeRankingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "总销售额")
    private BigDecimal totalPrice;

    @Schema(description = "总销售量")
    private Integer totalNum;

    @Schema(description = "商品图片")
    private String imagePath;

}
