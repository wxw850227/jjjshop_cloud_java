

package net.jjjshop.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "地区VO")
public class RegionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "父id")
    private Integer pid;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "层级 1 2 3 省市区县")
    private Integer level;

    @Schema(description = "children")
    private List<RegionVo> children;

    @Schema(description = "是否选中")
    private Boolean checked;

    @Schema(description = "选中下标")
    private List<Integer> index;

    @Schema(description = "选中下标")
    private Integer childIndex;
}
