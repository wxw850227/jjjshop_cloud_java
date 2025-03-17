package net.jjjshop.common.vo.setting;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "前端图片VO")
public class ImageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //有很多模块都用到了该图片Vo，顾提取出来
    @Schema(description = "图片id")
    private Integer fileId;

    @Schema(description = "商品图片主图")
    private String filePath;
}
