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
 * 配送模板主表
 *
 * @author jjjshop
 * @since 2022-06-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Delivery对象")
public class Delivery implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "模板id")
    @TableId(value = "delivery_id", type = IdType.AUTO)
    private Integer deliveryId;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "计费方式(10按件数 20按重量)")
    private Integer method;

    @Schema(description = "小程序d")
    private Integer tenantId;

    @Schema(description = "排序方式(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
