

package net.jjjshop.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.settings.Region;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "插件列表VO")
public class RegionVo extends Region {

    private static final long serialVersionUID = 1L;

    @Schema(description = "provinceId")
    private String provinceId;

    @Schema(description = "provinceId")
    private String cityId;
}
