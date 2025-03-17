package net.jjjshop.shop.vo.plus.coupon;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.plus.coupon.Coupon;
import net.jjjshop.shop.vo.product.ProductVo;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "优惠券Vo")
public class CouponVo extends Coupon {
    private static final long serialVersionUID = 1L;

    @Schema(description = "开始时间文本")
    private String startTimeText;

    @Schema(description = "结束时间文本")
    private String endTimeText;

    @Schema(description = "优惠券类型(10满减券 20折扣券)")
    private String couponTypeText;

    @Schema(description = "限制商品id")
    private List<Integer> productShowIds;

    @Schema(description = "限制商品")
    private List<ProductVo> productList;

    @Schema(description = "限制分类")
    private JSONObject categoryList;
}
