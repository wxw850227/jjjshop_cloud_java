package net.jjjshop.front.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "售后订单地址VO")
public class OrderRefundAddressVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer id;

    @Schema(description = "售后单id")
    private Integer orderRefundId;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

}
