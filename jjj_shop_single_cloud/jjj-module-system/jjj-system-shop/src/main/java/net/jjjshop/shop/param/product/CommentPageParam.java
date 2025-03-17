package net.jjjshop.shop.param.product;

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
@Schema(name = "CommentPageParam对象", description = "商品评价分页参数")
public class CommentPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评价状态(0=待审核 1=审核通过2=审核不通过)")
    private Integer status;

    @Schema(description = "搜索内容")
    private String name;

    @Schema(description = "评分 (10好评 20中评 30差评)")
    private Integer score;
}
