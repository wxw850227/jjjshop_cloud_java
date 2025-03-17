package net.jjjshop.common.settings.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "物流设置VO")
public class ExpressVo implements Serializable {

    private String expressName;
    private String expressCode;
    private Integer sort;

    public ExpressVo() {
        this.expressName = "";
        this.expressCode = "";
        this.sort = 0;
    }
}
