

package net.jjjshop.front.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.Product;
import net.jjjshop.common.entity.settings.Delivery;
import net.jjjshop.common.entity.settings.DeliveryRule;
import net.jjjshop.common.vo.product.ProductSkuVo;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "购买商品VO")
public class ProductBuyVo extends Product {

    private static final long serialVersionUID = 1L;

    @Schema(description = "购买商品sku")
    private ProductSkuVo productSku;

    @Schema(description = "购买数量")
    private Integer totalNum;

    @Schema(description = "购买规格")
    private String specSkuId;

    @Schema(description = "商品总价")
    private BigDecimal totalPrice;

    @Schema(description = "商品支付金额")
    private BigDecimal totalPayPrice;

    @Schema(description = "商品图片")
    private String productImage;

    @Schema(description = "运费")
    private BigDecimal expressPrice;

    @Schema(description = "运费模板规则")
    private List<DeliveryRule> deliveryRuleList;

    @Schema(description = "运费模板")
    private Delivery delivery;

    @Schema(description = "优惠券抵扣金额")
    private BigDecimal couponMoney;

    @Schema(description = "是否参与会员折扣")
    private Boolean isUserGrade;

    @Schema(description = "会员等级抵扣的金额")
    private BigDecimal gradeRatio;

    @Schema(description = "会员折扣的商品单价")
    private BigDecimal gradeProductPrice;

    @Schema(description = "会员折扣的总额差")
    private BigDecimal gradeTotalMoney;
}
