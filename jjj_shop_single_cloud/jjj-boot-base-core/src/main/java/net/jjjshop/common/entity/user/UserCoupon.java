package net.jjjshop.common.entity.user;

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
 * 用户优惠券记录表
 *
 * @author jjjshop
 * @since 2022-08-02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserCoupon对象")
public class UserCoupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "user_coupon_id", type = IdType.AUTO)
    private Integer userCouponId;

    @Schema(description = "优惠券id")
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

    @Schema(description = "有效期开始时间")
    private Date startTime;

    @Schema(description = "有效期结束时间")
    private Date endTime;

    @Schema(description = "适用范围(10全部商品 20指定商品)")
    private Integer applyRange;

    @Schema(description = "是否过期(0未过期 1已过期)")
    private Integer isExpire;

    @Schema(description = "是否已使用(0未使用 1已使用)")
    private Integer isUse;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
