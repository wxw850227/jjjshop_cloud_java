

package net.jjjshop.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.app.App;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "插件列表VO")
public class AppVo extends App {

    private static final long serialVersionUID = 1L;

    @Schema(description = "userName")
    private String userName;
}
