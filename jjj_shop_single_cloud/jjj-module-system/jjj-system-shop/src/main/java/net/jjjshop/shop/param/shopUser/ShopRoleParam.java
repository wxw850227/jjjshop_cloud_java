package net.jjjshop.shop.param.shopUser;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

import java.util.List;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ShopRole操作对象", description = "ShopRole操作对象")
public class ShopRoleParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "权限集合")
    private List<Integer> accessId;
}
