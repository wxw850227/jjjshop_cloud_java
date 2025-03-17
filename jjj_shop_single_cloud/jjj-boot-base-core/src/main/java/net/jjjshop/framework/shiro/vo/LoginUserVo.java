

package net.jjjshop.framework.shiro.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Add;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 登录用户对象，响应给前端
 **/
@Data
@Accessors(chain = true)
public class LoginUserVo implements Serializable {

    private static final long serialVersionUID = -1758338570596088158L;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空", groups = {Add.class})
    private String mobile;

}
