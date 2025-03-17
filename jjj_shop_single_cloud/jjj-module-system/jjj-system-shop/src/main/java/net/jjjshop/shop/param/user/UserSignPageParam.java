package net.jjjshop.shop.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "积分明细分页对象", description = "积分明细分页对象")
public class UserSignPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    //连续签到天数
    private Integer days;
    //签到时间
    private Integer signDate;
    //用户昵称
    private String nickName;
    private String startDate;
    private String endDate;
}
