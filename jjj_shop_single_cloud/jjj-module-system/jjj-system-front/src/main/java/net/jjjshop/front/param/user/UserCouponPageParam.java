package net.jjjshop.front.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "UserCouponPageParam")
public class UserCouponPageParam  extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "优惠券种类")
    private String dataType;

}
