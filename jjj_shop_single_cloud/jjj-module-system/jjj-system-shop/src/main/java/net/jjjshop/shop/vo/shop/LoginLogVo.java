package net.jjjshop.shop.vo.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "用户登陆日志VO")
public class LoginLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer loginLogId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录ip")
    private String ip;

    @Schema(description = "登录结果")
    private String result;

    @Schema(description = "创建时间")
    private Date createTime;
}
