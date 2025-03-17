package net.jjjshop.shop.vo.shopUser;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商城管理员VO")
public class ShopUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer shopUserId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "是否为超级管理员0不是,1是")
    private Boolean isSuper;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "角色名称")
    private List<Map> roleList;
}
