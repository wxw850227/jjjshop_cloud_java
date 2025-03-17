package net.jjjshop.shop.param.setting;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "物流公司查询参数", description = "物流公司查询参数")
public class ExpressParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "物流公司id")
    private Integer expressId;

    @Schema(description = "物流公司名称")
    private String expressName;

    @Schema(description = "物流公司代码 (快递100)")
    private String expressCode;

    @Schema(description = "微信小程序物流公司编码")
    private String wxCode;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;

    @Schema(description = "承运商id")
    private Integer carrierId;
}
