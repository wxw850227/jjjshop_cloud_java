

package net.jjjshop.front.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品列表VO")
public class ProductListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "产品id")
    private Integer productId;

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品一口价")
    private BigDecimal productPrice;

    @Schema(description = "划线价")
    private BigDecimal linePrice;

    @Schema(description = "产品编码")
    private String productNo;

    @Schema(description = "产品总库存")
    private Integer productStock;

    @Schema(description = "商品卖点")
    private String sellingPoint;

    @Schema(description = "产品分类id")
    private Integer categoryId;

    @Schema(description = "产品规格(10单规格 20多规格)")
    private Integer specType;

    @Schema(description = "销量")
    private Integer productSales;

    @Schema(description = "是否开启会员折扣(1开启 0关闭)")
    private Integer isEnableGrade;

    @Schema(description = "会员折扣设置(0默认等级折扣 1单独设置折扣)")
    private Integer isAloneGrade;

    @Schema(description = "是否虚拟，0否1是")
    private Integer isVirtual;

    @Schema(description = "单独设置折扣的配置")
    private String aloneGradeEquity;

    @Schema(description = "折扣金额类型(10百分比 20固定金额)")
    private Integer aloneGradeType;

    @Schema(description = "访问次数")
    private Integer viewTimes;

    @Schema(description = "限购数量0为不限")
    private Integer limitNum;

    @Schema(description = "可购买会员等级id，逗号隔开")
    private String gradeIds;

    @Schema(description = "是否会员折扣")
    private Boolean isUserGrade;

    // 附加字段
    @Schema(description = "图片")
    private String productImage;
}
