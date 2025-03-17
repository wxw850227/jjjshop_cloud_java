package net.jjjshop.common.entity.product;

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
 * 商品规格组记录表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ProductSpec对象")
public class ProductSpec implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "规格组id")
    @TableId(value = "spec_id", type = IdType.AUTO)
    private Integer specId;

    @Schema(description = "规格组名称")
    private String specName;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
