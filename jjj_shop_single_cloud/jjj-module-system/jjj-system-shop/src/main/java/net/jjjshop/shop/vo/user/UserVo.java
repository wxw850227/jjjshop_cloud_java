package net.jjjshop.shop.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.User;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "用户VO")
public class UserVo extends User {

    private static final long serialVersionUID = 1L;

    @Schema(description = "等级名称")
    private String gradeName;

    @Schema(description = "推荐人")
    private String refereeName;
}
