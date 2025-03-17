package net.jjjshop.shop.vo.product;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.ProductComment;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品评价VO")
public class CommentVo extends ProductComment {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片主图")
    private String imagePath;

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
