

package net.jjjshop.common.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Add;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AdminUser对象", description = "saas管理员用户")
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "ID不能为空", groups = {Update.class})
    @TableId(value = "admin_user_id", type = IdType.AUTO)
    private Long adminUserId;

    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空", groups = {Add.class})
    private String userName;

    @Schema(description = "昵称")
    private String password;

    @Schema(description = "盐值")
    private String salt;

    @Schema(description = "创建时间")
    @Null(message = "创建时间不用传")
    private Date createTime;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;

}
