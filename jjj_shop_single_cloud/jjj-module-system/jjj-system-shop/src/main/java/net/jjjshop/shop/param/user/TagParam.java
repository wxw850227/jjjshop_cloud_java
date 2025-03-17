package net.jjjshop.shop.param.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(name = "用户标签查询参数", description = "用户标签查询参数")
public class TagParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer tagId;

    @Schema(description = "标签名称")
    private String tagName;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
