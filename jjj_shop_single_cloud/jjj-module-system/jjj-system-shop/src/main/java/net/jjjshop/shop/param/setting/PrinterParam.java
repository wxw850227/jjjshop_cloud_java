package net.jjjshop.shop.param.setting;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(name = "小票打印机查询参数", description = "小票打印机查询参数")
public class PrinterParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "打印机id")
    private Integer printerId;

    @Schema(description = "打印机名称")
    private String printerName;

    @Schema(description = "打印机类型")
    private String printerType;

    @Schema(description = "打印机配置")
    private String printerConfig;

    @Schema(description = "打印联数(次数)")
    private Integer printTimes;

    @Schema(description = "排序 (数字越小越靠前)")
    private Integer sort;

    @Schema(description = "飞鹅云设置")
    private JSONObject feieYun;

    @Schema(description = "365设置")
    private JSONObject printCenter;

    @Schema(description = "芯烨云设置")
    private JSONObject xpYun;

}
