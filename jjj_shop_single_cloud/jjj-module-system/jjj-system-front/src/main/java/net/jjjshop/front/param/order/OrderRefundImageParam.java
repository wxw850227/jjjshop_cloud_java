package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "OrderRefundImageParam")
public class OrderRefundImageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "图片保存路径")
    private String filePath;

    @Schema(description = "图片Id")
    private Integer fileId;
}
