package net.jjjshop.shop.vo.app;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.app.App;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "appVo")
public class AppVo extends App {
    private static final long serialVersionUID = 1L;

    @Schema(description = "支付类型")
    private JSONObject payTypeJson;
}
