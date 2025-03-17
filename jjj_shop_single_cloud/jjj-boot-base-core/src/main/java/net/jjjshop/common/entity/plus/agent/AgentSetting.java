package net.jjjshop.common.entity.plus.agent;

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
 * 分销商设置表
 *
 * @author jjjshop
 * @since 2022-06-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AgentSetting对象")
public class AgentSetting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "设置项标示")
    @TableId(value = "set_key", type = IdType.AUTO)
    private String setKey;

    @Schema(description = "设置项描述")
    private String describe;

    @Schema(description = "设置内容(json格式)")
    private String setValues;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "更新时间")
    private Date updateTime;

}
