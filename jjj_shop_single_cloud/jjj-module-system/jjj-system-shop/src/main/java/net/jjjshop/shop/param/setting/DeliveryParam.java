package net.jjjshop.shop.param.setting;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(name = "运费模板", description = "运费模板新增修改参数")
public class DeliveryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "模板id")
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Integer deliveryId;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "计费方式(10按件数 20按重量)")
    private Integer method;

    @Schema(description = "排序方式(数字越小越靠前)")
    private Integer sort;

    @NotNull(message = "运费规则不能为空")
    @Schema(description = "运费规则)")
    private List<RuleParam> rule;

    @Data
    @Accessors(chain = true)
    @Schema(description = "运费规则")
    public static class RuleParam implements Serializable {
        private static final long serialVersionUID = 1L;
        private String region;
        private Double first;
        private BigDecimal firstFee;
        private Double additional;
        private BigDecimal additionalFee;

        public RuleParam() {

        }
    }
}
