package net.jjjshop.shop.param.store;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(name = "StoreClerkParam对象", description = "StoreClerkParam对象")
public class StoreClerkParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "店员id")
    private Integer clerkId;

    @Schema(description = "所属门店id")
    private Integer storeId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "店员姓名")
    private String realName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "状态(0禁用 1启用)")
    private Integer status;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
