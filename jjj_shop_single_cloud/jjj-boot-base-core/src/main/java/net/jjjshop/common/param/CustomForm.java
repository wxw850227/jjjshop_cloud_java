package net.jjjshop.common.param;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 模板参数
 */
@Data
@Accessors(chain = true)
@Schema(name = "自定义表单")
public class CustomForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字段类型")
    private String label;

    @Schema(description = "是否必填")
    private boolean status;

    @Schema(description = "字段名称")
    private String title;

    @Schema(description = "表单值")
    private String value;
}
