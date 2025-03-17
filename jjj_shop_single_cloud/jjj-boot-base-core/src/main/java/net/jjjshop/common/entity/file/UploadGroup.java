package net.jjjshop.common.entity.file;

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
 * 文件库分组记录表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UploadGroup对象")
public class UploadGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类id")
    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;

    @Schema(description = "文件类型image图片，video视频")
    private String groupType;

    @Schema(description = "分类名称")
    private String groupName;

    @Schema(description = "分类排序(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
