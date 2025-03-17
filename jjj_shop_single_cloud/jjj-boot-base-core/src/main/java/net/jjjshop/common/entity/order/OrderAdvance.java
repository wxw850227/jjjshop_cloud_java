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
 * 预售定金订单记录表
 *
 * @author jjjshop
 * @since 2022-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OrderAdvance对象")
public class OrderAdvance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = " 主键id")
    @TableId(value = "order_advance_id", type = IdType.AUTO)
    private Integer orderAdvanceId;

    @Schema(description = "预售商品id")
    private Integer advanceProductId;

    @Schema(description = "预售商品规格id")
    private Integer advanceProductSkuId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "预售结束时间")
    private Integer endTime;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "支付定金金额")
    private BigDecimal payPrice;

    @Schema(description = "支付方式(10余额支付 20微信支付30支付宝)")
    private Integer payType;

    @Schema(description = "余额抵扣金额")
    private BigDecimal balance;

    @Schema(description = "在线支付金额")
    private BigDecimal onlineMoney;

    @Schema(description = "付款状态(10未付款 20已付款)")
    private Integer payStatus;

    @Schema(description = "付款时间")
    private Integer payTime;

    @Schema(description = "订单状态10=>进行中，20=>已经取消，30=>已完成")
    private Integer orderStatus;

    @Schema(description = "预售订单支付结束时间")
    private Integer payEndTime;

    @Schema(description = "主订单id")
    private Integer orderId;

    @Schema(description = "支付来源,mp,wx")
    private String paySource;

    @Schema(description = "是否允许退款0不允许1允许")
    private Boolean moneyReturn;

    @Schema(description = "退款金额")
    private BigDecimal refundMoney;

    @Schema(description = "尾款立减金额")
    private BigDecimal reduceMoney;

    @Schema(description = "主订单号")
    private String mainOrderNo;

    @Schema(description = "是否已退款0否1是")
    private Boolean isRefund;

    @Schema(description = "app_id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
