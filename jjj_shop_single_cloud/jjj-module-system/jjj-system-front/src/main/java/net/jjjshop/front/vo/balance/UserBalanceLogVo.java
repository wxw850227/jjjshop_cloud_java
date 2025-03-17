package net.jjjshop.front.vo.balance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "用户余额明细VO")
public class UserBalanceLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "log_id")
    private Integer logId;

    @Schema(description = "user_id")
    private Integer userId;

    @Schema(description = "scene")
    private Integer scene;

    @Schema(description = "sceneText")
    private String sceneText;

    @Schema(description = "money")
    private BigDecimal money;

    @Schema(description = "description")
    private String description;

    @Schema(description = "remark")
    private String remark;

    @Schema(description = "create_time")
    private Date createTime;
}
