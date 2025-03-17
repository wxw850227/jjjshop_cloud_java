package net.jjjshop.shop.param.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(name = "User对象", description = "User对象")
public class UserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @Schema(description = "微信openid(唯一标示)")
    private String openId;

    @Schema(description = "微信公众号openid")
    private String mpopenId;

    @Schema(description = "openappid")
    private String appopenId;

    @Schema(description = "微信开放平台id")
    private String unionId;

    @Schema(description = "苹果用户")
    private String appUser;

    @Schema(description = "注册来源")
    private String regSource;

    @Schema(description = "微信昵称")
    @TableField("nickName")
    private String nickName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "盐值")
    private String salt;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "微信头像")
    @TableField("avatarUrl")
    private String avatarUrl;

    @Schema(description = "性别0=女1=男2=未知")
    private Integer gender;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "默认收货地址")
    private Integer addressId;

    @Schema(description = "用户可用余额")
    private BigDecimal balance;

    @Schema(description = "用户可用积分")
    private Integer points;

    @Schema(description = "用户总支付的金额")
    private BigDecimal payMoney;

    @Schema(description = "实际消费的金额(不含退款)")
    private BigDecimal expendMoney;

    @Schema(description = "会员等级id")
    private Integer gradeId;

    @Schema(description = "推荐人id")
    private Integer refereeId;

    @Schema(description = "累计积分")
    private Integer totalPoints;

    @Schema(description = "总邀请人数")
    private Integer totalInvite;

    @Schema(description = "已冻结佣金")
    private BigDecimal freezeMoney;

    @Schema(description = "累积提现佣金")
    private BigDecimal cashMoney;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "用户余额充值方式")
    private String balanceMode;

    @Schema(description = "用户余额充值备注")
    private String balanceRemark;

    @Schema(description = "用户余额充值数额")
    private BigDecimal money;

    @Schema(description = "用户积分充值方式")
    private String pointsMode;

    @Schema(description = "用户积分充值备注")
    private String pointsRemark;

    @Schema(description = "用户积分充值数额")
    private Integer value;

    @Schema(description = "用户充值方式,0表示余额充值，1表示积分充值")
    private Integer source;

    @Schema(description = "等级变更备注")
    private String remark;

    @Schema(description = "用户标签")
    private List<Integer> checkedTag;
}
