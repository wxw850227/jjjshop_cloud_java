

package net.jjjshop.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Add;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(description = "AppParam对象")
public class AppParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "tenantId")
    private Integer tenantId;

    @NotEmpty(message = "应用名称不能为空")
    @Schema(description = "应用名称")
    private String appName;

    @Schema(description = "是否回收")
    private Boolean isRecycle;

    @Schema(description = "是否过期")
    private Boolean noExpire;

    @Schema(description = "过期时间")
    private String expireTime;

    @NotEmpty(message = "管理员账号不能为空")
    @Schema(description = "管理员账号")
    private String userName;

    @NotEmpty(message = "登录密码不能为空", groups = {Add.class})
    @Schema(description = "登录密码")
    private String password;
}
