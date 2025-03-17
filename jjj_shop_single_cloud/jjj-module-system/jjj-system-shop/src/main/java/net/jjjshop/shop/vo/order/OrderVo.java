package net.jjjshop.shop.vo.order;

import com.alibaba.fastjson.JSONArray;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.Order;
import net.jjjshop.common.entity.settings.Express;
import net.jjjshop.common.entity.settings.ReturnAddress;
import net.jjjshop.common.vo.order.OrderProductVo;
import net.jjjshop.shop.vo.store.ExtractStoreVo;
import net.jjjshop.shop.vo.store.StoreClerkVo;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单详情VO")
public class OrderVo extends Order {

    private static final long serialVersionUID = 1L;

    @Schema(description = "购买用户")
    private String nickName;

    @Schema(description = "微信头像")
    private String avatarUrl;

    @Schema(description = "订单来源")
    private String orderSourceText;

    @Schema(description = "订单状态")
    private String orderStatusText;

    @Schema(description = "支付方式")
    private String payTypeText;

    @Schema(description = "配送方式")
    private String deliveryTypeText;

    @Schema(description = "支付状态文本")
    private String payStatusText;

    @Schema(description = "发货状态文本")
    private String deliveryStatusText;

    @Schema(description = "收货状态(10未收货 20已收货)文本")
    private String receiptStatusText;

    @Schema(description = "购买商品")
    private List<OrderProductVo> productList;

    @Schema(description = "地址详情")
    private OrderAddressVo address;

    @Schema(description = "所有物流公司")
    private List<Express> expressList;

    @Schema(description = "发货地址列表")
    private List<ReturnAddress> addressList;

    @Schema(description = "发货物流公司")
    private Express express;

    @Schema(description = "物流公司名称")
    private String expressName;

    @Schema(description = "后台更新价格类型")
    private String updatePriceSymbol;

    @Schema(description = "核销门店")
    private ExtractStoreVo extractStore;

    @Schema(description = "店员列表")
    private List<StoreClerkVo> shopClerkList;

    @Schema(description = "核销员工姓名")
    private String extractClerkName;

    @Schema(description = "核销门店")
    private String extractStoreName;

    @Schema(description = "用户手机号")
    private String mobile;

    @Schema(description = "定金")
    private BigDecimal money;

    @Schema(description = "尾款立减金额")
    private BigDecimal reduceMoney;

    @Schema(description = "尾款金额")
    private BigDecimal balancePayment;

    @Schema(description = "自定义表单数组")
    private JSONArray customForm;

}
