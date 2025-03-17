

package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * 登录 参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "LoginParam", description = "LoginParam")
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "验证码")
    private String code;

    @Schema(description = "验证码图片")
    private String codeImage;

    @Schema(description = "key值")
    private String codeKey;

    @Schema(description = "tenantId")
    private Integer tenantId;

}
