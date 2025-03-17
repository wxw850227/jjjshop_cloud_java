package net.jjjshop.shop.param.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "用户等级查询参数", description = "用户等级查询参数")
public class UserCashParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer id;

    @Schema(description = "申请状态 (10待审核 20审核通过 30驳回 40已打款)")
    private Integer applyStatus;

    @Schema(description = "驳回原因")
    private String rejectReason;
}
