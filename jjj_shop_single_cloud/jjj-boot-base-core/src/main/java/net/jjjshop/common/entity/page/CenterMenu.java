package net.jjjshop.common.entity.page;

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
 * 个人中心菜单
 *
 * @author jjjshop
 * @since 2022-07-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CenterMenu对象")
public class CenterMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "图片url")
    private String icon;

    @Schema(description = "排序(越小越靠前)")
    private Integer sort;

    @Schema(description = "跳转链接")
    private String path;

    @Schema(description = "链接名称")
    private String pathName;

    @Schema(description = "1显示0隐藏")
    private Integer isShow;

    @Schema(description = "系统菜单标记")
    private String sysTag;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
