package net.jjjshop.common.entity.settings;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 退货地址记录表
 *
 * @author jjjshop
 * @since 2022-07-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ReturnAddress对象")
public class ReturnAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "退货地址id")
    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;

    @Schema(description = "是否删除0=显示1=隐藏")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
