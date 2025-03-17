package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OrderPageParam对象")
public class OrderPageParam extends BasePageOrderParam {

    private final static long serialVersionUID = 1L;

    @Schema(description = "订单分类")
    private String type;

    @Schema(description = "用户Id")
    private Integer userId;

}
