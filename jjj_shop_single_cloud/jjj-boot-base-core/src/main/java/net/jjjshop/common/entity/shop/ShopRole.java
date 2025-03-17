package net.jjjshop.common.entity.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家用户角色表
 *
 * @author jjjshop
 * @since 2022-07-09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopRole对象")
public class ShopRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "排序(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
