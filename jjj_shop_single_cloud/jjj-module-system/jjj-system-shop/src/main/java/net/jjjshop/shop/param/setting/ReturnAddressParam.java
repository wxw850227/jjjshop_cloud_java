package net.jjjshop.shop.param.setting;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "退货地址查询参数", description = "退货地址查询参数")
public class ReturnAddressParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "退货地址id")
    private Integer addressId;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;
}
