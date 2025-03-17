package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "OrderRankingParam对象")
public class HomeRankingParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "开始搜索时间")
    private String startDate;

    @Schema(description = "结束搜索时间")
    private String endDate;

    @Schema(description = "搜索种类")
    private String type;

    @Schema(description = "商户ID")
    private Integer shopSupplierId;

}
