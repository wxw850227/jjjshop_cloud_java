package net.jjjshop.shop.param.app;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "app升级参数", description = "app升级参数")
public class AppUpdateParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "update_id")
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
}
