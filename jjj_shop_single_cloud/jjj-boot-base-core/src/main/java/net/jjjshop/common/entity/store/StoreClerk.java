package net.jjjshop.common.entity.store;

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
 * 商家门店店员表
 *
 * @author jjjshop
 * @since 2022-07-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "StoreClerk对象")
public class StoreClerk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "店员id")
    @TableId(value = "clerk_id", type = IdType.AUTO)
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
