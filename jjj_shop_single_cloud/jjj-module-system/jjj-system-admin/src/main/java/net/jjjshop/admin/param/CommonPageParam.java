package net.jjjshop.admin.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "没有参数的分页")
public class CommonPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
