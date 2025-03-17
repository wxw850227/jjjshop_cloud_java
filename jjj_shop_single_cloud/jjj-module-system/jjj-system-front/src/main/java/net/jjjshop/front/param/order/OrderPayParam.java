

package net.jjjshop.front.param.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序登录参数
 */
@Data
@Accessors(chain = true)
@Schema(description = "订单参数对象")
public class OrderPayParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "orderId")
    private Integer orderId;

    @Schema(description = "支付来源")
    private String paySource;

    @Schema(description = "支付方式")
    private Integer payType;

    @Schema(description = "是否使用余额")
    private Boolean useBalance;
}
