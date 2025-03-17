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
 * 产品分类表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ProductCategory对象")
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "产品分类id")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "上级分类id")
    private Integer parentId;

    @Schema(description = "分类图片id")
    private Integer imageId;

    @Schema(description = "排序方式(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
