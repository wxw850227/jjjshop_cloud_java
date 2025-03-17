

package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "HomeParam", description = "HomeParam")
public class HomeParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "商品排序类型,1=total_num正序,2=total_num倒序,3=total_price正序,4=total_price倒序")
    private Integer productType;

    @Schema(description = "商品销售时间,1=最近30天,2=今天.3=最近7天,4本月,5本年")
    private String productTime;

    @Schema(description = "销售时间,1=最近7天,2=最近15天,3=最近30天")
    private Integer saleTime;

    @Schema(description = "用户时间,1=最近7天,2=最近15天,3=最近30天")
    private Integer userTime;

    @Schema(description = "商户ID")
    private Integer shopSupplierId;

    @Schema(description = "tenantId")
    private Integer tenantId;

}
