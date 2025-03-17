package net.jjjshop.common.entity.settings;

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
 * 配送模板区域及运费表
 *
 * @author jjjshop
 * @since 2022-06-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "DeliveryRule对象")
public class DeliveryRule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "规则id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "配送模板id")
    private Integer deliveryId;

    @Schema(description = "可配送区域(城市id集)")
    private String region;

    @Schema(description = "首件(个)/首重(Kg)")
    private Double first;

    @Schema(description = "运费(元)")
    private BigDecimal firstFee;

    @Schema(description = "续件/续重")
    private Double additional;

    @Schema(description = "续费(元)")
    private BigDecimal additionalFee;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
