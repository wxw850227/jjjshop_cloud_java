

package net.jjjshop.front.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.shiro.service.LoginToken;
import net.jjjshop.framework.shiro.vo.LoginUserVo;

@Data
@Accessors(chain = true)
@Schema(description = "登录用户信息TokenVO")
public class LoginUserTokenVo implements LoginToken {
    private static final long serialVersionUID = 1L;

    @Schema(description = "token")
    private String token;

    /**
     * 登录用户对象
     */
    private LoginUserVo loginUserVo;
}
