package net.jjjshop.common.settings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.settings.DeliveryRule;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "配送规则VO对象")
public class DeliveryRuleVo extends DeliveryRule {

    private static final long serialVersionUID = 1L;

    @Schema(description = "省份")
    private List<Integer> province;

    @Schema(description = "城市")
    private List<String> citys;
}
