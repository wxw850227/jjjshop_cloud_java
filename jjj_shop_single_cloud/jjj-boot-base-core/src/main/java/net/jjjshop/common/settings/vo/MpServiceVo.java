package net.jjjshop.common.settings.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "客服设置VO")
public class MpServiceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String qq;
    private String wechat;
    private String mpImage;

    public MpServiceVo() {
        this.qq = "123456";
        this.wechat = "123456";
        this.mpImage = "";
    }
}
