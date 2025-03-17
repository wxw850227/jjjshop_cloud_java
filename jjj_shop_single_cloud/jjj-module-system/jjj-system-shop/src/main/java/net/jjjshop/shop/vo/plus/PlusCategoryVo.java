package net.jjjshop.shop.vo.plus;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.plus.PlusCategory;
import net.jjjshop.common.entity.shop.ShopAccess;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "插件显示Vo")
public class PlusCategoryVo extends PlusCategory {
    private static final long serialVersionUID = 1L;

    @Schema(description = "使用该标签的会员人数")
    private List<ShopAccess> children;
}
