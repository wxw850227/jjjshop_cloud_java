package net.jjjshop.front.vo.settings;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "物流详情VO")
public class ExpressDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "物流公司id")
    private Integer expressId;

    @Schema(description = "物流公司名称")
    private String expressName;

    @Schema(description = "物流编号")
    private String expressNo;

    @Schema(description = "快递100物流实时信息")
    private JSONObject express;
}
