package net.jjjshop.shop.param.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "积分明细分页对象", description = "积分明细分页对象")
public class UserPointsLogPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "开始时间")
    private Date startDate;

    @Schema(description = "场景")
    private Date endDate;

}
