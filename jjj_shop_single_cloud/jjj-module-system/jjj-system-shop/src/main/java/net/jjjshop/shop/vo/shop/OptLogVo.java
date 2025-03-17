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
@Schema(description = "用户操作日志VO")
public class OptLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer optLogId;

    @Schema(description = "用户id")
    private Integer shopUserId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "访问url")
    private String url;

    @Schema(description = "请求类型")
    private String requestType;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "浏览器信息")
    private String agent;

    @Schema(description = "操作内容")
    private String content;

    @Schema(description = "登录ip")
    private String ip;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "姓名")
    private String realName;

}
