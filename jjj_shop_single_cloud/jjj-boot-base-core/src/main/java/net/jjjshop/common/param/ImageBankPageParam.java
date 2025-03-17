

package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 系统图库 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UploadFilePageParam对象", description = "app查询参数")
public class ImageBankPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "上级id")
    private Integer parentId;
}
