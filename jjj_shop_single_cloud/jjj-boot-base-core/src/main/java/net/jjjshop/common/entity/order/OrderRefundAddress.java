package net.jjjshop.common.entity.order;

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
 * 售后单退货地址记录表
 *
 * @author jjjshop
 * @since 2022-07-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OrderRefundAddress对象")
public class OrderRefundAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "售后单id")
    private Integer orderRefundId;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
