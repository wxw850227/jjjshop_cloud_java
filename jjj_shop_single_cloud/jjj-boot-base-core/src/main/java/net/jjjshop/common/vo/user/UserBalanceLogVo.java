package net.jjjshop.common.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.UserBalanceLog;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "用户余额明细VO")
public class UserBalanceLogVo extends UserBalanceLog {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "微信头像")
    private String avatarurl;
}
