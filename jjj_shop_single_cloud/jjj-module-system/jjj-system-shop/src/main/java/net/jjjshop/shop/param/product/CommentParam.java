package net.jjjshop.shop.param.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 商品评价 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "CommentParam对象", description = "商品评价参数")
public class CommentParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评价id")
    @TableId(value = "comment_id", type = IdType.AUTO)
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

    @Schema(description = "商品图片主图")
    private String imagePath;

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品一口价")
    private BigDecimal productPrice;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "微信昵称")
    @TableField("nickName")
    private String nickName;

    @Schema(description = "评价图片")
    private List<String> image;
}
