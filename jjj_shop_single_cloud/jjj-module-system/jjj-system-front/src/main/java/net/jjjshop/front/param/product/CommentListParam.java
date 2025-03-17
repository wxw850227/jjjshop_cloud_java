package net.jjjshop.front.param.product;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.vo.setting.ImageVo;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 商品评价 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(description = "CommentListParam对象")
public class CommentListParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评论图片id")
    private Integer commentId;

    @Schema(description = "评分 (10好评 20中评 30差评)")
    private Integer score;

    @Schema(description = "评价内容")
    private String content;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "商品id")
    private Integer productId;

    @Schema(description = "订单商品id")
    private Integer orderProductId;

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品一口价")
    private BigDecimal productPrice;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "微信昵称")
    @TableField("nickName")
    private String nickname;

    @Schema(description = "图片")
    private List<ImageVo> imageList;
}
