package net.jjjshop.common.util.diy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * diy元素格式
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "diyItem")
public class DiyItem implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @Schema(description = "组件名称")
    private String name;

    @Schema(description = "组件类型")
    private String type;

    @Schema(description = "组件类别")
    private String group;

    @Schema(description = "参数")
    private JSONObject params;

    @Schema(description = "样式")
    private JSONObject style;

    @Schema(description = "选择的数据")
    private JSONArray data;

    @Schema(description = "默认数据")
    private JSONArray defaultData;
}
