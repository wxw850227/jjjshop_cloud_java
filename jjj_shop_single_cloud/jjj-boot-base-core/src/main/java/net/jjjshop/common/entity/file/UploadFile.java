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
 * 文件库记录表
 *
 * @author jjjshop
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UploadFile对象")
public class UploadFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件id")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    @Schema(description = "存储方式")
    private String storage;

    @Schema(description = "文件分组id")
    private Integer groupId;

    @Schema(description = "存储域名")
    private String fileUrl;

    @Schema(description = "文件路径")
    private String fileName;

    @Schema(description = "文件大小(字节)")
    private Long fileSize;

    @Schema(description = "文件类型")
    private String fileType;

    @Schema(description = "文件真实名")
    private String realName;

    @Schema(description = "文件扩展名")
    private String extension;

    @Schema(description = "是否为c端用户上传")
    private Integer isUser;

    @Schema(description = "是否已回收")
    private Integer isRecycle;

    @Schema(description = "软删除")
    private Integer isDelete;

    @Schema(description = "应用id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
