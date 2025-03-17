package net.jjjshop.shop.param.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "用户操作日志分页对象", description = "用户操作日志分页对象")
public class OptLogPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户真实姓名")
    private String username;
}
