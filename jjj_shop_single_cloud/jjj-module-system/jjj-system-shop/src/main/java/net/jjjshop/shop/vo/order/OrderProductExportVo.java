package net.jjjshop.shop.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单商品导出VO")
public class OrderProductExportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品规格信息")
    private String productAttr;

    @Schema(description = "购买数量")
    private Integer totalNum;

    @Schema(description = "商品总价(数量×单价)")
    private BigDecimal totalPrice;
}
