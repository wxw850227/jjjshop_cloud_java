package net.jjjshop.shop.param.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "退货地址分页查询参数", description = "退货地址分页查询参数")
public class ReturnAddressPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
