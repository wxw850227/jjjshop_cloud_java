package net.jjjshop.shop.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单导出VO")
public class OrderExportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "商品总金额(不含优惠折扣)")
    private BigDecimal totalPrice;

    @Schema(description = "优惠券抵扣金额")
    private BigDecimal couponMoney;

    @Schema(description = "实际付款金额(包含运费)")
    private BigDecimal payPrice;

    @Schema(description = "买家留言")
    private String buyerRemark;

    @Schema(description = "付款时间")
    private Date payTime;

    @Schema(description = "运费金额")
    private BigDecimal expressPrice;

    @Schema(description = "物流单号")
    private String expressNo;

    @Schema(description = "发货时间")
    private Date deliveryTime;

    @Schema(description = "收货时间")
    private Date receiptTime;

    @Schema(description = "微信支付交易号")
    private String transactionId;

    @Schema(description = "是否已评价(0否 1是)")
    private String isCommentText;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "购买用户")
    private String nickName;

    @Schema(description = "订单状态")
    private String orderStatusText;

    @Schema(description = "配送方式")
    private String deliveryTypeText;

    @Schema(description = "支付状态文本")
    private String payStatusText;

    @Schema(description = "发货状态文本")
    private String deliveryStatusText;

    @Schema(description = "购买商品")
    private String product;

    @Schema(description = "发货物流公司名称")
    private String expressName;

    @Schema(description = "详细地址")
    private String detailRegion;

    @Schema(description = "收货状态(10未收货 20已收货)文本")
    private String receiptStatusText;

    @Schema(description = "收货人姓名")
    private String addressName;

    @Schema(description = "收货人电话")
    private String addressPhone;

    @Schema(description = "核销门店")
    private String extractStoreName;

    @Schema(description = "联系人")
    private String extractLinkman;

    @Schema(description = "联系电话")
    private String extractPhone;

    @Schema(description = "支付方式")
    private String payTypeText;

}
