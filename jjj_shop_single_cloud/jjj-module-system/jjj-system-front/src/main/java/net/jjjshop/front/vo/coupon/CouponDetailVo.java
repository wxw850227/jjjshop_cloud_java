package net.jjjshop.front.vo.coupon;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.front.vo.product.ProductListVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "优惠券详情VO")
public class CouponDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "优惠券id")
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Integer couponId;

    @Schema(description = "优惠券名称")
    private String name;

    @Schema(description = "优惠券颜色(10蓝 20红 30紫 40黄)")
    private Integer color;

    @Schema(description = "优惠券类型(10满减券 20折扣券)")
    private Integer couponType;

    @Schema(description = "满减券-减免金额")
    private BigDecimal reducePrice;

    @Schema(description = "折扣券-折扣率(0-100)")
    private Integer discount;

    @Schema(description = "最低消费金额")
    private BigDecimal minPrice;

    @Schema(description = "到期类型(10领取后生效 20固定时间)")
    private Integer expireType;

    @Schema(description = "领取后生效-有效天数")
    private Integer expireDay;

    @Schema(description = "固定时间-开始时间")
    private Date startTime;

    @Schema(description = "固定时间-结束时间")
    private Date endTime;

    @Schema(description = "适用范围(10全部商品 20指定商品 30指定分类)")
    private Integer applyRange;

    @Schema(description = "限制商品id")
    private String productIds;

    @Schema(description = "限制分类id")
    private String categoryIds;

    @Schema(description = "发放总数量(-1为不限制)")
    private Integer totalNum;

    @Schema(description = "已领取数量")
    private Integer receiveNum;

    @Schema(description = "排序方式(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "是否显示领券中心，0否1是")
    private Integer showCenter;

    @Schema(description = "优惠限制0不显示1不可与促销同时2不可与等级优惠同时3不可于促销和等级同时")
    private Integer freeLimit;

    @Schema(description = "商品列表")
    private List<ProductListVo> productList;

    @Schema(description = "优惠券颜色文本")
    private String colorText;

    @Schema(description = "优惠券类型(10满减券 20折扣券)")
    private String couponTypeText;

    @Schema(description = "有效期开始时间")
    private String startTimeText;

    @Schema(description = "有效期开始时间")
    private String endTimeText;

    @Schema(description = "优惠券状态")
    private JSONObject state;
}
