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
 * 售后单记录表
 *
 * @author jjjshop
 * @since 2022-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OrderRefund对象")
public class OrderRefund implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "售后单id")
    @TableId(value = "order_refund_id", type = IdType.AUTO)
    private Integer orderRefundId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "订单商品id")
    private Integer orderProductId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "售后类型(10退货退款 20换货 30退款)")
    private Integer type;

    @Schema(description = "用户申请原因(说明)")
    private String applyDesc;

    @Schema(description = "商家审核状态(0待审核 10已同意 20已拒绝)")
    private Integer isAgree;

    @Schema(description = "商家拒绝原因(说明)")
    private String refuseDesc;

    @Schema(description = "实际退款金额")
    private BigDecimal refundMoney;

    @Schema(description = "用户是否发货(0未发货 1已发货)")
    private Integer isUserSend;

    @Schema(description = "用户发货时间")
    private Date sendTime;

    @Schema(description = "用户发货物流公司id")
    private String expressId;

    @Schema(description = "用户发货物流单号")
    private String expressNo;

    @Schema(description = "商家收货状态(0未收货 1已收货)")
    private Integer isReceipt;

    @Schema(description = "售后单状态(0进行中 10已拒绝 20已完成 30已取消)")
    private Integer status;

    @Schema(description = "平台发货时间")
    private Date deliverTime;

    @Schema(description = "平台发货物流公司id")
    private String sendExpressId;

    @Schema(description = "平台发货物流单号")
    private String sendExpressNo;

    @Schema(description = "平台是否发货(0未发货 1已发货)")
    private Integer isPlateSend;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
