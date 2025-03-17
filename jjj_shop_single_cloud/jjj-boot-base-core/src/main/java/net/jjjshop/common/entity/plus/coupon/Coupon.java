package net.jjjshop.common.entity.plus.coupon;

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
 * 优惠券记录表
 *
 * @author jjjshop
 * @since 2022-07-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Coupon对象")
public class Coupon implements Serializable {
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

    @Schema(description = "软删除")
    private Integer isDelete;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
