package net.jjjshop.shop.vo.setting;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "打印机VO")
public class PrinterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "打印机id")
    private Integer printerId;

    @Schema(description = "打印机名称")
    private String printerName;

    @Schema(description = "打印机类型")
    private String printerType;

    @Schema(description = "打印机配置")
    private JSONObject printerConfig;

    @Schema(description = "打印联数(次数)")
    private Integer printTimes;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;


}
