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
@Schema(name = "账单明细分页对象", description = "账单明细分页对象")
public class UserBalanceLogPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "场景Text")
    private String sceneText;

    @Schema(description = "场景value")
    private Integer scene;

    @Schema(description = "开始时间")
    private String startDate;

    @Schema(description = "结束时间")
    private String endDate;


}
