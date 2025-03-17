package net.jjjshop.front.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "UserFavoritePageParam")
public class UserFavoritePageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "种类")
    private Integer type;

    @Schema(description = "用户Id")
    private Integer userId;

}
