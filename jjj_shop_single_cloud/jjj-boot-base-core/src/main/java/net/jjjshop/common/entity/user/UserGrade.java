package net.jjjshop.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户会员等级表
 *
 * @author jjjshop
 * @since 2022-06-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserGrade对象")
public class UserGrade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "等级ID")
    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

    @Schema(description = "等级名称")
    private String name;

    @Schema(description = "是否开放0，否1是")
    private Integer openMoney;

    @Schema(description = "升级条件")
    private Integer upgradeMoney;

    @Schema(description = "等级权益,百分比")
    private Integer equity;

    @Schema(description = "是否默认，1是，0否")
    private Integer isDefault;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "权重")
    private Integer weight;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
