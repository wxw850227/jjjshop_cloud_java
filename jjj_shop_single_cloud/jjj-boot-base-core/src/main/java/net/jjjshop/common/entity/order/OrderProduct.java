package net.jjjshop.common.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品记录表
 *
 * @author jjjshop
 * @since 2022-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OrderProduct对象")
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "order_product_id", type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Integer orderProductId;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品封面图id")
    private Integer imageId;

    @Schema(description = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    @Schema(description = "规格类型(10单规格 20多规格)")
    private Integer specType;

    @Schema(description = "商品sku标识")
    private String specSkuId;

    @Schema(description = "商品规格id")
    private Integer productSkuId;

    @Schema(description = "商品规格信息")
    private String productAttr;

    @Schema(description = "商品详情")
    private String content;

    @Schema(description = "商品编码")
    private String productNo;

    @Schema(description = "商品价格(单价)")
    private BigDecimal productPrice;

    @Schema(description = "商品划线价")
    private BigDecimal linePrice;

    @Schema(description = "商品重量(Kg)")
    private Double productWeight;

    @Schema(description = "是否存在会员等级折扣")
    private Boolean isUserGrade;

    @Schema(description = "会员折扣比例(0-10)")
    private Integer gradeRatio;

    @Schema(description = "会员折扣的商品单价")
    private BigDecimal gradeProductPrice;

    @Schema(description = "会员折扣的总额差")
    private BigDecimal gradeTotalMoney;

    @Schema(description = "优惠券折扣金额")
    private BigDecimal couponMoney;

    @Schema(description = "购买数量")
    private Integer totalNum;

    @Schema(description = "商品总价(数量×单价)")
    private BigDecimal totalPrice;

    @Schema(description = "实际付款价(折扣和优惠后)")
    private BigDecimal totalPayPrice;

    @Schema(description = "是否已评价(0否 1是)")
    private Integer isComment;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "虚拟物品内容")
    private String virtualContent;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
