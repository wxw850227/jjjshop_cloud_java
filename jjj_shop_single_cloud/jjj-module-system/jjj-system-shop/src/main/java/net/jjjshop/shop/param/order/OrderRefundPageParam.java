package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "售后单分页参数")
public class OrderRefundPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "搜索订单号")
    private String orderNo;

    @Schema(description = "查询条件：起始日期")
    private String startDate;

    @Schema(description = "查询条件：结束日期")
    private String endDate;

    @Schema(description = "售后类型")
    private Integer type;

    @Schema(description = "售后单状态(选项卡)")
    private Integer status;
}
