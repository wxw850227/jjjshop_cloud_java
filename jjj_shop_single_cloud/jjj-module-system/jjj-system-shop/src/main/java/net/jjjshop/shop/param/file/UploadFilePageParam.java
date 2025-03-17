

package net.jjjshop.shop.param.file;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "app查询参数")
public class UploadFilePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分组id")
    private Integer groupId;

    @Schema(description = "文件类型")
    private String fileType;
}
