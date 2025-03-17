package net.jjjshop.shop.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.Order;
import net.jjjshop.common.entity.order.OrderRefund;
import net.jjjshop.common.entity.order.OrderRefundAddress;
import net.jjjshop.common.entity.settings.Express;
import net.jjjshop.common.entity.settings.ReturnAddress;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.common.vo.setting.ImageVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "售后单VO")
public class OrderRefundVo extends OrderRefund {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片")
    private OrderProductVo orderProduct;

    @Schema(description = "用户")
    private User user;

    @Schema(description = "订单创建时间")
    private Date orderCreateTime;

    @Schema(description = "商家处理文本信息")
    private String isAgreeText;

    @Schema(description = "售后类型：")
    private String typeText;

    @Schema(description = "处理状态：")
    private String statusText;

    @Schema(description = "用户退货物流信息")
    private Express express;

    @Schema(description = "物流公司信息")
    private List<Express> expressList;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "订单编号")
    private Order order;

    @Schema(description = "退货地址列表")
    private List<ReturnAddress> addressList;

    @Schema(description = "售后单图片")
    private List<ImageVo> images;

    @Schema(description = "退货地址")
    private OrderRefundAddress address;

    @Schema(description = "商家发货物流公司")
    private String sendExpressName;

    @Schema(description = "定金")
    private BigDecimal money;

    @Schema(description = "尾款立减金额")
    private BigDecimal reduceMoney;

    @Schema(description = "尾款金额")
    private BigDecimal balancePayment;

    @Schema(description = "是否允许退定金")
    private Boolean moneyReturn;

}
