

package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单参数
 */
@Data
@Accessors(chain = true)
@Schema(description = "订单参数对象")
public class OrderBuyParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "发货方式")
    private Integer delivery;

    @Schema(description = "上门自提门店id")
    private Integer storeId;

    @Schema(description = "使用优惠券id")
    private Integer couponId;

    @Schema(description = "支付渠道来源")
    private String paySource;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "购买商品数量")
    private Integer productNum;

    @Schema(description = "商品skuId")
    private String specSkuId;

    @Schema(description = "自提电话")
    private String phone;

    @Schema(description = "自提联系人")
    private String linkman;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "购物车")
    private String cartIds;
}
