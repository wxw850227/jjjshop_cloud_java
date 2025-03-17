

package net.jjjshop.framework.common.param;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * ID参数
 */
@Data
@Schema(description = "ID参数")
public class IdParam implements Serializable {
    private static final long serialVersionUID = -5353973980674510450L;

    @NotNull(message = "ID不能为空")
    private Long id;

}
