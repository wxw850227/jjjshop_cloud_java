package net.jjjshop.front.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "AppWx对象")
public class PhoneRegisterParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "手机号")
    private String code;

    private String regSource;

    @Schema(description = "推荐人id")
    private Integer refereeId;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "邀请好友")
    private Integer invitationId;
}
