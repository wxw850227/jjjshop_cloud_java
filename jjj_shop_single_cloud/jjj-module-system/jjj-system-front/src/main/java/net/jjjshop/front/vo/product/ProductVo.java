

package net.jjjshop.front.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.Product;
import net.jjjshop.common.entity.product.ProductComment;
import net.jjjshop.common.entity.product.ProductSpec;
import net.jjjshop.common.entity.settings.DeliveryRule;
import net.jjjshop.common.vo.product.ProductImageVo;
import net.jjjshop.common.vo.product.ProductSkuVo;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品详情VO")
public class ProductVo extends Product {

    private static final long serialVersionUID = 1L;

    @Schema(description = "图片")
    private List<ProductImageVo> image;

    @Schema(description = "详情图片")
    private List<ProductImageVo> contentImage;

    @Schema(description = "商品sku")
    private List<ProductSkuVo> skuList;

    @Schema(description = "商品sku")
    private ProductSkuVo productSku;

    @Schema(description = "商品spec")
    private List<ProductSpec> specList;

    @Schema(description = "物流规则")
    private DeliveryRule deliveryRule;

    @Schema(description = "商品评论")
    private List<ProductComment> commentData;

    @Schema(description = "商品销量")
    private Integer productSales;

    @Schema(description = "商品视频封面")
    private String videoFilePath;

    @Schema(description = "商品视频路径")
    private String posterFilePath;

    @Schema(description = "是否会员折扣")
    private Boolean isUserGrade;

    @Schema(description = "详情视频封面")
    private String contentPoster;

    @Schema(description = "详情视频路径")
    private String contentVideo;
}
