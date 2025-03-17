

package net.jjjshop.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 管理员重置用户密码参数
 **/
@Data
@Accessors(chain = true)
@Schema(description = "权限菜单参数")
public class ShopAccessParam implements Serializable {

    private static final long serialVersionUID = 5364321420976152005L;

    @Schema(description = "主键id")
    private Integer accessId;

    @NotEmpty(message = "权限名称不能为空")
    @Schema(description = "权限名称")
    private String name;

    @NotEmpty(message = "路由地址不能为空")
    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "父级id")
    private Integer parentId;

    @Schema(description = "排序(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "重定向名称")
    private String redirectName;

    @Schema(description = "是否是路由 0=不是1=是")
    private Integer isRoute;

    @Schema(description = "是否是菜单 0不是 1是")
    private Integer isMenu;

    @Schema(description = "别名(废弃)")
    private String alias;

    @Schema(description = "是否显示1=显示0=不显示")
    private Integer isShow;

    @Schema(description = "描述")
    private String remark;

}
