

package net.jjjshop.front.param.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(description = "CategoryParam对象")
public class CategoryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "id")
    private Integer id;

    @NotNull(message = "上级分类id不能为空")
    @Schema(description = "上级分类id")
    private Integer parentId;

    @NotEmpty(message = "分类名称不能为空")
    @Schema(description = "分类名称")
    private String name;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序")
    private Integer sort;

    @NotNull(message = "分类图片不能为空")
    @Schema(description = "分类图片")
    private Integer imageId;
}
