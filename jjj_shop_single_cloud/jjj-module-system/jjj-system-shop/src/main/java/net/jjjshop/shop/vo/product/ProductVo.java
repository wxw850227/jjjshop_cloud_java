package net.jjjshop.shop.vo.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.product.Product;
import net.jjjshop.common.vo.product.ProductImageVo;
import net.jjjshop.common.vo.product.ProductSkuVo;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品分类VO")
public class ProductVo extends Product {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品图片主图")
    private String imagePath;

    @Schema(description = "商品分类名称")
    private String categoryName;

    //编辑使用
    @Schema(description = "商品单独折扣")
    private JSONObject aloneGradeEquityJson;

    //编辑使用
    @Schema(description = "商品sku")
    private List<ProductSkuVo> sku;

    //编辑使用
    @Schema(description = "商品图片集合")
    private List<ProductImageVo> image;

    //编辑使用
    @Schema(description = "详情图片集合")
    private List<ProductImageVo> contentImage;

    @Schema(description = "视频文件")
    private String videoFilePath;

    @Schema(description = "视频封面")
    private String posterFilePath;

    @Schema(description = "自定义表单数组")
    private JSONArray customForm;

    @Schema(description = "详情视频封面")
    private String contentPoster;

    @Schema(description = "详情视频路径")
    private String contentVideo;
}
