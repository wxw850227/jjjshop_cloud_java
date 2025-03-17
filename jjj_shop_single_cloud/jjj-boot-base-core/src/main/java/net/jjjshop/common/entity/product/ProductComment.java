package net.jjjshop.common.entity.product;

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
import java.util.Date;

/**
 * 订单评价记录表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ProductComment对象")
public class ProductComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "评价id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Integer commentId;

    @Schema(description = "评分 (10好评 20中评 30差评)")
    private Integer score;

    @Schema(description = "评价内容")
    private String content;

    @Schema(description = "是否为图片评价")
    private Integer isPicture;

    @Schema(description = "评价排序")
    private Integer sort;

    @Schema(description = "评价状态(0=待审核 1=审核通过2=审核不通过)")
    private Integer status;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "订单商品id")
    private Integer orderProductId;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "软删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
