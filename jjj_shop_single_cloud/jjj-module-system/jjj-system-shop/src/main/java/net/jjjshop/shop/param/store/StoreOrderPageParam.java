package net.jjjshop.shop.param.store;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

import java.util.List;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "订单核销分页列表", description = "订单核销分页列表")
public class StoreOrderPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "搜索栏内容")
    private String search;

    @Schema(description = "搜索栏店名Id")
    private Integer storeId;

    @Schema(description = "开始时间")
    private String startDate;

    @Schema(description = "结束时间")
    private String endDate;

    @Schema(description = "时间")
    private List<String> createTime;
}
