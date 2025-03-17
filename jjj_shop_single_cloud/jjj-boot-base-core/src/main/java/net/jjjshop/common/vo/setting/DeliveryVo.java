package net.jjjshop.common.vo.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.settings.Delivery;
import net.jjjshop.common.entity.settings.DeliveryRule;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "配送模板VO")
public class DeliveryVo extends Delivery {

    private static final long serialVersionUID = 1L;

    //编辑使用
    @Schema(description = "运费规则")
    private List<DeliveryRule> rule;
}
