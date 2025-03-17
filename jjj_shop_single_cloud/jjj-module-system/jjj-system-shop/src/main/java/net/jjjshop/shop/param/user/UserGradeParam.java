package net.jjjshop.shop.param.user;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(name = "用户等级查询参数", description = "用户等级查询参数")
public class UserGradeParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "等级ID")
    private Integer gradeId;

    @Schema(description = "等级名称")
    private String name;

    @Schema(description = "是否开放0，否1是")
    private Integer openMoney;

    @Schema(description = "升级条件")
    private Integer upgradeMoney;

    @Schema(description = "积分是否开放0否1是")
    private Integer openPoints;

    @Schema(description = "累计积分升级")
    private Integer upgradePoints;

    @Schema(description = "邀请是否开放0否1是")
    private Integer openInvite;

    @Schema(description = "邀请人数升级")
    private Integer upgradeInvite;

    @Schema(description = "等级权益,百分比")
    private Integer equity;

    @Schema(description = "是否默认，1是，0否")
    private Integer isDefault;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "权重")
    private Integer weight;

    @Schema(description = "奖励积分")
    private Integer givePoints;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
