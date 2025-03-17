

package net.jjjshop.framework.ip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * IP地址
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "IpAddress对象")
public class IpAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String ipStart;

    private String ipEnd;

    @Schema(description = "区域")
    private String area;

    @Schema(description = "运营商")
    private String operator;

    private Long ipStartNum;

    private Long ipEndNum;

}
