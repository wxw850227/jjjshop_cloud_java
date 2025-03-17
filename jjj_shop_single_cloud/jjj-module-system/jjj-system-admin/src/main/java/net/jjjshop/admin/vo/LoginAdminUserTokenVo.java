

package net.jjjshop.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.shiro.service.LoginToken;
import net.jjjshop.framework.shiro.vo.LoginAdminUserVo;

@Data
@Accessors(chain = true)
@Schema(description = "登录用户信息TokenVO")
public class LoginAdminUserTokenVo implements LoginToken {

    private static final long serialVersionUID = -2138450422989081056L;

    @Schema(description = "token")
    private String token;

    /**
     * 登录用户对象
     */
    private LoginAdminUserVo loginAdminUserVo;
}
