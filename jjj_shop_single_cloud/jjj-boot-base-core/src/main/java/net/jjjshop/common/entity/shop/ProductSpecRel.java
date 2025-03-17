package net.jjjshop.common.entity.shop;

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
 * 商品与规格值关系记录表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ProductSpecRel对象")
public class ProductSpecRel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "产品id")
    private Integer productId;

    @Schema(description = "规格组id")
    private Integer specId;

    @Schema(description = "规格值id")
    private Integer specValueId;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
