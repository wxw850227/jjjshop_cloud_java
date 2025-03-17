package net.jjjshop.shop.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.UserGradeLog;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "用户等级明细VO")
public class UserGradeLogVo extends UserGradeLog {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "微信头像")
    private String avatarUrl;

    @Schema(description = "变更前的等级名称")
    private String oldGradeName;

    @Schema(description = "变更后的等级名称")
    private String newGradeName;

}
