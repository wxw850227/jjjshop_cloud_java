package net.jjjshop.front.vo.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "物流公司列表VO")
public class ExpressListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "物流公司id")
    private Integer expressId;

    @Schema(description = "物流公司名称")
    private String expressName;

}
