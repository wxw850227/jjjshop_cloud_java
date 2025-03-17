package net.jjjshop.common.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.OrderProduct;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单VO")
public class OrderProductVo extends OrderProduct {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片")
    private String imagePath;

    @Schema(description = "商品图片")
    private String orderCreateTime;
}
