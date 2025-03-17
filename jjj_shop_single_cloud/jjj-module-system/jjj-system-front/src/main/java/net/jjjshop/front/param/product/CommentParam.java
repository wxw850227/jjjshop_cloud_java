package net.jjjshop.front.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * 商品评价 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(description = "CommentParam对象")
public class CommentParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评价参数")
    private List<CommentListParam> params;

    @Schema(description = "订单Id")
    private Integer orderId;
}
