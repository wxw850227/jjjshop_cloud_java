

package net.jjjshop.front.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 微信小程序登录参数
 */
@Data
@Accessors(chain = true)
@Schema(description = "AppWx对象")
public class AppWxParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "invitationId")
    private Integer invitationId;

    @NotEmpty(message = "code不能为空")
    @Schema(description = "code")
    private String code;

    @NotEmpty(message = "encryptedData不能为空")
    @Schema(description = "加密信息")
    private String encryptedData;

    @NotEmpty(message = "iv不能为空")
    @Schema(description = "iv")
    private String iv;

    @NotEmpty(message = "signature不能为空")
    @Schema(description = "signature")
    private String signature;

    @Schema(description = "推荐人id")
    private Integer refereeId;

    @NotEmpty(message = "管理员账号不能为空")
    @Schema(description = "管理员账号")
    private String source;

    @Schema(description = "token")
    private String token;

    @NotEmpty(message = "tenantId不能为空")
    @Schema(description = "tenantId")
    private Integer tenantId;

    @NotEmpty(message = "用户信息不能为空")
    @Schema(description = "userInfo")
    private String userInfo;
}
