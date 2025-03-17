package net.jjjshop.common.entity.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家用户记录表
 *
 * @author jjjshop
 * @since 2022-06-23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopUser对象")
public class ShopUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "主键id")
    @TableId(value = "shop_user_id", type = IdType.AUTO)
    private Integer shopUserId;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String userName;

    @NotBlank(message = "盐值不能为空")
    @Schema(description = "盐值")
    private String salt;

    @NotBlank(message = "登录密码不能为空")
    @Schema(description = "登录密码")
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "是否为超级管理员0不是,1是")
    private Boolean isSuper;

    @Schema(description = "0=显示1=伪删除")
    private Integer isDelete;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
