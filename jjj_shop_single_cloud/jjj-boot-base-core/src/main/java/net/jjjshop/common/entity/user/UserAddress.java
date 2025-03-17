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
 * 用户收货地址表
 *
 * @author jjjshop
 * @since 2022-08-02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserAddress对象")
public class UserAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "所在省份id")
    private Integer provinceId;

    @Schema(description = "所在城市id")
    private Integer cityId;

    @Schema(description = "所在区id")
    private Integer regionId;

    @Schema(description = "新市辖区(该字段用于记录region表中没有的市辖区)")
    private String district;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
