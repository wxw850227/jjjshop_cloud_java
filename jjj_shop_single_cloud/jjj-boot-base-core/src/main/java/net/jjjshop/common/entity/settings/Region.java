package net.jjjshop.common.entity.settings;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author jjjshop
 * @since 2022-06-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Region对象")
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "父id")
    private Integer pid;

    @Schema(description = "简称")
    private String shortname;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "全称")
    private String mergerName;

    @Schema(description = "层级 1 2 3 省市区县")
    private Integer level;

    @Schema(description = "拼音")
    private String pinyin;

    @Schema(description = "长途区号")
    private String code;

    @Schema(description = "邮编")
    private String zipCode;

    @Schema(description = "首字母")
    private String first;

    @Schema(description = "经度")
    private String lng;

    @Schema(description = "纬度")
    private String lat;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否删除0否1是")
    private Boolean isDelete;

}
