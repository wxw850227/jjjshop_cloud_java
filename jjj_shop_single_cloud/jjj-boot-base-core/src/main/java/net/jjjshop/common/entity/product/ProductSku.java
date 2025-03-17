package net.jjjshop.common.entity.product;

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
 * 商品规格表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ProductSku对象")
public class ProductSku implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "产品规格id")
    @TableId(value = "product_sku_id", type = IdType.AUTO)
    private Integer productSkuId;

    @Schema(description = "产品id")
    private Integer productId;

    @Schema(description = "产品sku记录索引 (由规格id组成)")
    private String specSkuId;

    @Schema(description = "规格图片id")
    private Integer imageId;

    @Schema(description = "产品编码")
    private String productNo;

    @Schema(description = "产品价格")
    private BigDecimal productPrice;

    @Schema(description = "产品划线价")
    private BigDecimal linePrice;

    @Schema(description = "产品底价")
    private BigDecimal lowPrice;

    @Schema(description = "当前库存数量")
    private Integer stockNum;

    @Schema(description = "产品销量(废弃)")
    private Integer productSales;

    @Schema(description = "产品重量(Kg)")
    private Double productWeight;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
