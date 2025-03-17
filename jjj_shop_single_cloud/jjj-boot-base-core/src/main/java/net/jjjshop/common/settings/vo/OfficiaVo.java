package net.jjjshop.common.settings.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "公众号关注VO")
public class OfficiaVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer status;

    public OfficiaVo() {
        this.status = 0;
    }
}
