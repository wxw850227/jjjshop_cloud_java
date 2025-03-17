package net.jjjshop.front.vo.order;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.vo.setting.ImageVo;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "订单评价VO")
public class OrderCommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Schema(description = "微信昵称")
    private String nickName;

    @Schema(description = "图片")
    private String productImage;

    @Schema(description = "图片")
    private List<ImageVo> imageList;
}
