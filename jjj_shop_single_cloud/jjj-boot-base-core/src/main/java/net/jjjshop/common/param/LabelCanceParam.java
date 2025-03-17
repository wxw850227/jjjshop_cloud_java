package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "LabelCancelParam对象", description = "电子面单取消参数")
public class LabelCanceParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单Id")
    private Integer orderId;

    @Schema(description = "物流公司Id")
    private Integer expressId;

    @Schema(description = "电子面单设置Id")
    private Integer labelSettingId;

    @Schema(description = "物流单号")
    private String expressNo;

    @Schema(description = "电子面单订单号")
    private String kdOrderNum;

    @Schema(description = "tenantId")
    private Long tenantId;
}
