

package net.jjjshop.shop.param.page;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 参数对象
 */
@Data
@Accessors(chain = true)
@Schema(description = "Center新增修改参数")
public class CenterParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "menu_id")
    private Integer menuId;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "menu_id")
    private Integer pageId;

    @Schema(description = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    private String name;

    @Schema(description = "图片url")
    @NotEmpty(message = "图片url不能为空")
    private String icon;

    @Schema(description = "排序(越小越靠前)")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "跳转链接")
    @NotEmpty(message = "跳转链接不能为空")
    private String path;

    @Schema(description = "params")
    private String params;

    @Schema(description = "链接名称")
    @NotEmpty(message = "链接名称不能为空")
    private String pathName;

    @Schema(description = "1显示0隐藏")
    private Integer isShow;
}
