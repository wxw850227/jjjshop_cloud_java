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
 * diy页面表
 *
 * @author jjjshop
 * @since 2022-07-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Page对象")
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "页面id")
    @TableId(value = "page_id", type = IdType.AUTO)
    private Integer pageId;

    @Schema(description = "页面类型(10首页 20自定义页)")
    private Integer pageType;

    @Schema(description = "页面名称")
    private String pageName;

    @Schema(description = "页面数据")
    private String pageData;

    @Schema(description = "是否设置首页1是")
    private Boolean isDefault;

    @Schema(description = "tenantId")
    private Integer tenantId;

    @Schema(description = "软删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
