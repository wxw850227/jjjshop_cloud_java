package net.jjjshop.common.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单记录表
 *
 * @author jjjshop
 * @since 2022-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Order对象")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "交易流水号")
    private String tradeNo;

    @Schema(description = "商品总金额(不含优惠折扣)")
    private BigDecimal totalPrice;

    @Schema(description = "订单金额(含优惠折扣)")
    private BigDecimal orderPrice;

    @Schema(description = "优惠券id")
    private Integer couponId;

    @Schema(description = "优惠券抵扣金额")
    private BigDecimal couponMoney;

    @Schema(description = "实际付款金额(包含运费)")
    private BigDecimal payPrice;

    @Schema(description = "后台修改的订单金额（差价）")
    private BigDecimal updatePrice;

    @Schema(description = "买家留言")
    private String buyerRemark;

    @Schema(description = "支付方式(10余额支付 20微信支付)")
    private Integer payType;

    @Schema(description = "支付来源,mp,wx")
    private String paySource;

    @Schema(description = "付款状态(10未付款 20已付款)")
    private Integer payStatus;

    @Schema(description = "付款时间")
    private Date payTime;

    @Schema(description = "支付截止时间")
    private Date payEndTime;

    @Schema(description = "配送方式(10快递配送 20上门自提 30无需物流)")
    private Integer deliveryType;

    @Schema(description = "自提门店id")
    private Integer extractStoreId;

    @Schema(description = "核销店员id")
    private Integer extractClerkId;

    @Schema(description = "运费金额")
    private BigDecimal expressPrice;

    @Schema(description = "物流公司id")
    private Integer expressId;

    @Schema(description = "物流公司")
    private String expressCompany;

    @Schema(description = "物流单号")
    private String expressNo;

    @Schema(description = "发货状态(10未发货 20已发货)")
    private Integer deliveryStatus;

    @Schema(description = "发货时间")
    private Date deliveryTime;

    @Schema(description = "收货状态(10未收货 20已收货)")
    private Integer receiptStatus;

    @Schema(description = "收货时间")
    private Date receiptTime;

    @Schema(description = "订单状态10=>进行中，20=>已经取消，30=>已完成")
    private Integer orderStatus;

    @Schema(description = "订单是否已结算(0未结算 1已结算)")
    private Integer isSettled;

    @Schema(description = "微信支付交易号")
    private String transactionId;

    @Schema(description = "是否已评价(0否 1是)")
    private Integer isComment;

    @Schema(description = "订单来源(10普通 20积分 30拼团 40砍价 50秒杀 60礼包购)")
    private Integer orderSource;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "拼团等活动失败退款")
    private Integer isRefund;

    @Schema(description = "拼团状态 10拼单中 20拼单成功 30拼单失败")
    private Integer assembleStatus;

    @Schema(description = "活动id，对应拼团，秒杀，砍价活动id")
    private Integer activityId;

    @Schema(description = "商家取消订单备注")
    private String cancelRemark;

    @Schema(description = "是否自动发货1自动0手动")
    private Integer virtualAuto;

    @Schema(description = "虚拟物品内容")
    private String virtualContent;

    @Schema(description = "余额抵扣金额")
    private BigDecimal balance;

    @Schema(description = "在线支付金额")
    private BigDecimal onlineMoney;

    @Schema(description = "退款金额")
    private BigDecimal refundMoney;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
