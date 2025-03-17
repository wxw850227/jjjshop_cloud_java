

package net.jjjshop.shop.param.shopUser;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopUser对象", description = "ShopUser对象")
public class ShopUserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer shopUserId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "登录密码")
    private String password;

    @Schema(description = "确认密码")
    private String confirmPassword;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "用户角色")
    private List<Integer> roleId;
}
