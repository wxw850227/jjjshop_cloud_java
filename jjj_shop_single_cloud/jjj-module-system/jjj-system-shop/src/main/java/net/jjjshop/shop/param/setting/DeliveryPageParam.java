package net.jjjshop.shop.param.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "运费模板分页对象", description = "运费模板分页对象")
public class DeliveryPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
