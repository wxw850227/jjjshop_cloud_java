package net.jjjshop.front.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 商品评价 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "CommentPageParam对象")
public class CommentPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "产品id")
    private Integer productId;

    @Schema(description = "评分 (10好评 20中评 30差评)")
    private Integer score;
}
