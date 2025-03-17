package net.jjjshop.common.entity.settings;

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
 * 物流公司记录表
 *
 * @author jjjshop
 * @since 2022-07-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Express对象")
public class Express implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "物流公司id")
    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    @Schema(description = "物流公司名称")
    private String expressName;

    @Schema(description = "物流公司代码 (快递100)")
    private String expressCode;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
