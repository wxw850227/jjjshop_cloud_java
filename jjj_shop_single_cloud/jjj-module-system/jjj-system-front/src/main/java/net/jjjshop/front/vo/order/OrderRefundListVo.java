package net.jjjshop.front.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(description = "售后订单列表VO")
public class OrderRefundListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "售后单id")
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
    private Integer sendTime;

    @Schema(description = "用户发货物流公司id")
    private String expressId;

    @Schema(description = "用户发货物流单号")
    private String expressNo;

    @Schema(description = "商家收货状态(0未收货 1已收货)")
    private Integer isReceipt;

    @Schema(description = "售后单状态(0进行中 10已拒绝 20已完成 30已取消)")
    private Integer status;

    @Schema(description = "平台发货时间")
    private Integer deliverTime;

    @Schema(description = "平台发货物流公司id")
    private String sendExpressId;

    @Schema(description = "平台发货物流单号")
    private String sendExpressNo;

    @Schema(description = "平台是否发货(0未发货 1已发货)")
    private Integer isPlateSend;

    @Schema(description = "售后订单货物信息")
    private OrderProductVo orderProduct;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "售后单状态(0进行中 10已拒绝 20已完成 30已取消)")
    private String stateText;

}
