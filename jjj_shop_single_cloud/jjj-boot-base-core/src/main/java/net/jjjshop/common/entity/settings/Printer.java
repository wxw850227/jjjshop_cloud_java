package net.jjjshop.common.entity.settings;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 小票打印机记录表
 *
 * @author jjjshop
 * @since 2022-07-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Printer对象")
public class Printer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "打印机id")
    @TableId(value = "printer_id", type = IdType.AUTO)
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

    @Schema(description = "是否删除0=显示1=隐藏")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
