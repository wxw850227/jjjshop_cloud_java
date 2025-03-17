package net.jjjshop.common.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "商品分类VO")
public class ProductImageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "图片id")
    private Integer imageId;

    @Schema(description = "商品图片主图")
    private String filePath;
}
