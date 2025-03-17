package net.jjjshop.shop.vo.store;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.store.Store;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "门店VO")
public class StoreVo extends Store {

    private static final long serialVersionUID = 1L;

    @Schema(description = "地址坐标")
    private String coordinate;

    @Schema(description = "商家图片")
    private String logoImagePath;

    @Schema(description = "核销状态")
    private String isCheckText;

    @Schema(description = "支持状态")
    private String statusText;
}
