

package net.jjjshop.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.settings.Region;

/**
 * 部门 查询参数对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "RegionParam对象")
public class RegionParam extends Region {
    private static final long serialVersionUID = 1L;

    @Schema(description = "所属省份")
    private Integer provinceId;

    @Schema(description = "所属城市")
    private Integer cityId;
}
