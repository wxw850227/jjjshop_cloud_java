

package net.jjjshop.front.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.UserCart;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "购物车商品详情VO")
public class ProductCartVo extends UserCart {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片")
    private String productImage;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品名称")
    private BigDecimal productPrice;

    @Schema(description = "规格属性")
    private String productAttr;

    @Schema(description = "库存")
    private Integer stockNum;

    @Schema(description = "规格id")
    private String specSkuId;

    @Schema(description = "规格类型")
    private Integer specType;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "产品状态(10上架 20仓库中 30回收站)")
    private Integer productStatus;

    @Schema(description = "是否会员折扣价")
    private Boolean isUserGrade;

    @Schema(description = "是否允许等级折扣")
    private Integer isEnableGrade;

    @Schema(description = "单独设置折扣的配置")
    private String aloneGradeEquity;

    @Schema(description = "会员折扣设置(0默认等级折扣 1单独设置折扣)")
    private Integer isAloneGrade;

    @Schema(description = "折扣金额类型(10百分比 20固定金额)")
    private Integer aloneGradeType;
}
