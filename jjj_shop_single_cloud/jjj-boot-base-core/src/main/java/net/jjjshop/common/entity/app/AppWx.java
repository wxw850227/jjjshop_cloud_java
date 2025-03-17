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
 * 微信小程序记录表
 *
 * @author jjjshop
 * @since 2022-07-03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AppWx对象")
public class AppWx implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "tenantId")
    @TableId(value = "tenant_id", type = IdType.INPUT)
    private Integer tenantId;

    @Schema(description = "小程序AppID")
    private String wxappId;

    @Schema(description = "小程序AppSecret")
    private String wxappSecret;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
