package net.jjjshop.front.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.UserFavorite;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "登录用户信息TokenVO")
public class UserFavoriteVo extends UserFavorite {

    private static final long serialVersionUID = 1L;

    @Schema(description = "图片")
    private String productImage;

    @Schema(description = "商品销量")
    private Integer productId;

    @Schema(description = "商品名字")
    private String productName;

    @Schema(description = "商品价格")
    private BigDecimal productPrice;

    @Schema(description = "商品价格")
    private BigDecimal linePrice;
}
