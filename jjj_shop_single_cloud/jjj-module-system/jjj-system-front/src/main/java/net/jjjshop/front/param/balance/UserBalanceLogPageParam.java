package net.jjjshop.front.param.balance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "UserBalanceLogPageParam对象")
public class UserBalanceLogPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "查询方式")
    private String type;

}
