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
 * 管理员操作记录表
 *
 * @author jjjshop
 * @since 2022-08-15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ShopOptLog对象")
public class ShopOptLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "opt_log_id", type = IdType.AUTO)
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

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

}
