package net.jjjshop.shop.vo.order;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.order.OrderAddress;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "订单收货地址VO")
public class OrderAddressVo extends OrderAddress {

    private static final long serialVersionUID = 1L;

    @Schema(description = "详细地址")
    private JSONObject region;
}
