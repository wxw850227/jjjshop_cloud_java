package net.jjjshop.shop.vo.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.file.UploadFile;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "上传文件VO")
public class UploadFileVo extends UploadFile {

    private static final long serialVersionUID = 1L;

    @Schema(description = "文件路径")
    private String filePath;
}
