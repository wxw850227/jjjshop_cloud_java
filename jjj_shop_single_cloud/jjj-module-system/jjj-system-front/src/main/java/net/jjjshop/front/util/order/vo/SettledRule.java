package net.jjjshop.front.util.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "订单结算规则")
public class SettledRule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "优惠券抵扣")
    private Boolean isCoupon;

    @Schema(description = "会员等级折扣")
    private Boolean isUserGrade;

    public SettledRule(){
        this.isCoupon = true;
        this.isUserGrade = true;
    }
}
