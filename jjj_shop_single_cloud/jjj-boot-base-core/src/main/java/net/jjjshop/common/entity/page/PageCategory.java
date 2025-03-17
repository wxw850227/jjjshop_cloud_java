package net.jjjshop.common.entity.page;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * app分类页模板
 *
 * @author jjjshop
 * @since 2022-07-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "PageCategory对象")
public class PageCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "tenantId")
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Integer tenantId;

    @Schema(description = "分类页样式(10一级分类[大图] 11一级分类[小图] 20二级分类)")
    private Integer categoryStyle;

    @Schema(description = "分享标题")
    private String shareTitle;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
