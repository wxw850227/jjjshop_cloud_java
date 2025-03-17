package net.jjjshop.common.settings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "获取手机号VO")
public class GetPhoneVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> areaType;
    private Integer sendDay;

    public GetPhoneVo() {
        this.areaType = null;
        this.sendDay = 7;
    }
}
