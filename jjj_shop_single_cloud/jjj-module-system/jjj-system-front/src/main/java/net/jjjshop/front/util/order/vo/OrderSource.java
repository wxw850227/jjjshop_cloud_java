package net.jjjshop.front.util.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "订单来源")
public class OrderSource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "活动id")
    private Integer activityId;

    @Schema(description = "source")
    private Integer source;

    public OrderSource(){
        this.source = 0;
        this.activityId = 0;
    }
}
