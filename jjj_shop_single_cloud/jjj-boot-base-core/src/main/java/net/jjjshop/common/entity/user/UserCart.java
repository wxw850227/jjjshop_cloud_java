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
 * 用户购物车
 *
 * @author jjjshop
 * @since 2022-08-02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserCart对象")
public class UserCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "商品")
    private Integer productId;

    @Schema(description = "商品规格")
    private String specSkuId;

    @Schema(description = "商品数量")
    private Integer totalNum;

    @Schema(description = "加入时价格")
    private BigDecimal joinPrice;

    @Schema(description = "app_id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
