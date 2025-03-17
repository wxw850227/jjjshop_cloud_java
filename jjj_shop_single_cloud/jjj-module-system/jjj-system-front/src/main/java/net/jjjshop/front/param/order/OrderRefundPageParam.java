package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OrderRefundPageParam对象")
public class OrderRefundPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户Id")
    private Integer userId;

    @Schema(description = "售后订单状态")
    private Integer state;
}
