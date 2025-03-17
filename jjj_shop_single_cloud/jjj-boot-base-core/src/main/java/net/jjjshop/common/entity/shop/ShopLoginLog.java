package net.jjjshop.common.entity.shop;

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
 * 管理员登录记录表
 *
 * @author jjjshop
 * @since 2022-08-15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopLoginLog对象")
public class ShopLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "login_log_id", type = IdType.AUTO)
    private Integer loginLogId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录ip")
    private String ip;

    @Schema(description = "登录结果")
    private String result;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
