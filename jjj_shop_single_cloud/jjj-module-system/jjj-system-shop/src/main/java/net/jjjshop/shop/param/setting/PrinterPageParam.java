package net.jjjshop.shop.param.setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小票打印机分页查询参数", description = "小票打印机分页查询参数")
public class PrinterPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
