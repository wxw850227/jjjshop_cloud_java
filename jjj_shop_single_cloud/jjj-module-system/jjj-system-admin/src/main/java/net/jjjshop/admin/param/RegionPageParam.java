

package net.jjjshop.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "RegionPageParam对象")
public class RegionPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "地区名称")
    private String name;

    @Schema(description = "地区级别,1省，2市3，区")
    private Integer level;

    @Schema(description = "省id")
    private Integer provinceId;

    @Schema(description = "市id")
    private Integer cityId;
}
