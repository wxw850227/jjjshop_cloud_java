package net.jjjshop.common.entity.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家用户权限表
 *
 * @author jjjshop
 * @since 2022-06-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopAccess对象")
public class ShopAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "主键id不能为空")
    @Schema(description = "主键id")
    @TableId(value = "access_id", type = IdType.INPUT)
    private Integer accessId;

    @NotBlank(message = "权限名称不能为空")
    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "父级id")
    private Integer parentId;

    @Schema(description = "排序(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "重定向名称")
    private String redirectName;

    @Schema(description = "是否是路由 0=不是1=是")
    private Integer isRoute;

    @Schema(description = "是否是菜单 0不是 1是")
    private Integer isMenu;

    @Schema(description = "别名(废弃)")
    private String alias;

    @Schema(description = "是否显示1=显示0=不显示")
    private Integer isShow;

    @Schema(description = "插件分类id")
    private Integer plusCategoryId;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "app_id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
