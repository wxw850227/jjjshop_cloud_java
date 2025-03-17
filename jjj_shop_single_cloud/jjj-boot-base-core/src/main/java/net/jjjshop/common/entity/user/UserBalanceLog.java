package net.jjjshop.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户余额变动明细表
 *
 * @author jjjshop
 * @since 2022-07-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserBalanceLog对象")
public class UserBalanceLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "余额变动场景(10用户充值 20用户消费 30管理员操作 40订单退款)")
    private Integer scene;

    @Schema(description = "变动金额")
    private BigDecimal money;

    @Schema(description = "描述/说明")
    private String description;

    @Schema(description = "管理员备注")
    private String remark;

    @Schema(description = "小程序商城id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
