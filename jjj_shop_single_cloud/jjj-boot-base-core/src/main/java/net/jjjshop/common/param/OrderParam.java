package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(name = "OrderParam对象", description = "订单参数")
public class OrderParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单Id")
    private Integer orderId;

    @Schema(description = "物流编号")
    private String expressNo;

    @Schema(description = "是否单包裹")
    private Boolean isSingle;

    @Schema(description = "是否电子面单发货")
    private Boolean isLabel;

    @Schema(description = "物流公司Id")
    private Integer expressId;

    @Schema(description = "电子面单模板ID")
    private Integer templateId;

    @Schema(description = "电子面单设置ID")
    private Integer labelSettingId;

    @Schema(description = "发货地址ID")
    private Integer addressId;

    @Schema(description = "是否取消")
    private Boolean isCancel;

    @Schema(description = "电子面单任务ID")
    private String taskId;

    @Schema(description = "tenantId")
    private Long tenantId;
}
