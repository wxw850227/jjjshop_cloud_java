package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.vo.setting.ImageVo;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "OrderRefundParam")
public class OrderRefundParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单Id")
    private Integer orderProductId;

    @Schema(description = "用户售后申请说明")
    private String content;

    @Schema(description = "用户售后种类")
    private Integer type;

    @Schema(description = "售后订单上传图片")
    private List<ImageVo> images;

}
