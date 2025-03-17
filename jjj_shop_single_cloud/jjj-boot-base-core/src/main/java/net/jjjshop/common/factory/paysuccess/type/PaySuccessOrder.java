package net.jjjshop.common.factory.paysuccess.type;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回订单信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "支付成功订单")
public class PaySuccessOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "支付状态")
    private Integer payStatus;

    @Schema(description = "应用id")
    private Integer tenantId;
}
