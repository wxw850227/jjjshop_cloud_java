

package net.jjjshop.framework.shiro.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.shiro.service.LoginToken;

@Data
@Accessors(chain = true)
@Schema(description = "登录用户信息TokenVO")
public class LoginShopUserTokenVo implements LoginToken {

    private static final long serialVersionUID = 1L;

    @Schema(description = "token")
    private String token;

    /**
     * 登录用户对象
     */
    private LoginShopUserVo loginShopUserVo;

    @Schema(description = "商城登录logo")
    private String logoUrl;
}
