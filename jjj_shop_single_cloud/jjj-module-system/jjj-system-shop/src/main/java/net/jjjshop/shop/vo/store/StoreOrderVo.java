package net.jjjshop.shop.vo.store;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.store.StoreOrder;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单核销VO")
public class StoreOrderVo extends StoreOrder {

    private static final long serialVersionUID = 1L;

    @Schema(description = "店名")
    private String storeName;

    @Schema(description = "店员姓名")
    private String realName;

    @Schema(description = "核销订单类型")
    private String orderTypeText;

    @Schema(description = "核销订单编号")
    private String orderNo;
}
