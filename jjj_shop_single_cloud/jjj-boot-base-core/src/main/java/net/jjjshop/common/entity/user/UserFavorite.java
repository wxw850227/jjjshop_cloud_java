package net.jjjshop.common.entity.user;

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
 * 我的收藏
 *
 * @author jjjshop
 * @since 2022-08-02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserFavorite对象")
public class UserFavorite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "favorite_id", type = IdType.AUTO)
    private Integer favoriteId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "商品")
    private Integer productId;

    @Schema(description = "app_id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
