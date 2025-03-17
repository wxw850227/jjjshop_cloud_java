package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 模板参数
 */
@Data
@Accessors(chain = true)
@Schema(name = "模板参数对象", description = "模板参数对象")
public class SignParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "支付渠道来源")
    private String paySource;

    @Schema(description = "url")
    private String url;

    @Schema(description = "tenantId")
    private Long tenantId;
}
