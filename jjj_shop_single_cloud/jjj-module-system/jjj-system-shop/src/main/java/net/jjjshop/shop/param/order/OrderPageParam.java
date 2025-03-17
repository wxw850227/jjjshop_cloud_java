

package net.jjjshop.shop.param.order;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "订单分页参数")
public class OrderPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "搜索订单号")
    private String orderNo;

    @Schema(description = "查询条件：起始日期")
    private String startDate;

    @Schema(description = "查询条件：结束日期")
    private String endDate;

    @Schema(description = "查询条件：配送方式")
    private Integer deliveryType;

    @Schema(description = "订单来源(10普通 20积分 30拼团 40砍价 50秒杀 60礼包购 70抽奖 80预售)")
    private Integer orderSource;

    @Schema(description = "查询条件：订单状态")
    private String dataType;

    @Schema(description = "会员昵称|手机号|ID")
    private String search;
}
