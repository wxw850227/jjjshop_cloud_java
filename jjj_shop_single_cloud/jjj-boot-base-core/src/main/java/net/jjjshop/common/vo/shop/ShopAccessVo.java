

package net.jjjshop.common.vo.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.shop.ShopAccess;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "插件列表VO")
public class ShopAccessVo extends ShopAccess {

    private static final long serialVersionUID = -2138450422989081056L;

    @Schema(description = "children")
    private List<ShopAccessVo> children;
}
