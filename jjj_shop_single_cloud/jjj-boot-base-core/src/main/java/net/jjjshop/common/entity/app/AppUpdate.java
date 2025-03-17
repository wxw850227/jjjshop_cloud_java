package net.jjjshop.common.entity.app;

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
 * app升级记录表
 *
 * @author jjjshop
 * @since 2022-07-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AppUpdate对象")
public class AppUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "update_id", type = IdType.AUTO)
    private Integer updateId;

    @Schema(description = "tenantId")
    private Integer tenantId;

    @Schema(description = "android版本号")
    private String versionAndroid;

    @Schema(description = "ios版本号")
    private String versionIos;

    @Schema(description = "热更新包下载地址")
    private String wgtUrl;

    @Schema(description = "安卓整包升级地址")
    private String pkgUrlAndroid;

    @Schema(description = "ios整包升级地址")
    private String pkgUrlIos;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
