

package net.jjjshop.common.vo.order;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "支付成功数据VO")
public class PayDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "附属信息")
    private JSONObject attach;

    @Schema(description = "交易流水号")
    private String transaction_id;
}
