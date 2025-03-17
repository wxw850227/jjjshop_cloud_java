package net.jjjshop.common.param;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
//@Schema(description = "玖玖云ERP插件设置Param")
@Schema(description = "玖玖云ERP插件设置Param")
public class JjyErpParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "开启erp推送")
    private Boolean isOpenErp;

    @Schema(description = "玖玖云appkey")
    private String jjyAppkey;

    @Schema(description = "玖玖云secret")
    private String jjySecret;

    @Schema(description = "玖玖云公钥")
    private String jjyPublickey;

    @Schema(description = "玖玖云私钥")
    private String jjyPrivatekey;

    @Schema(description = "玖玖云域名地址")
    private String jjyUrl;
}
