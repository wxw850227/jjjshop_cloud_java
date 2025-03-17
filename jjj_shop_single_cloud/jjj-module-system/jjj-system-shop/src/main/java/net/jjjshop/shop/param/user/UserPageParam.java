

package net.jjjshop.shop.param.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.pagination.BasePageOrderParam;

import java.util.Date;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "用户查询分页参数", description = "用户查询分页参数")
public class UserPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "等级id")
    private Integer gradeId;

    @Schema(description = "搜索参数")
    private String search;

    @Schema(description = "微信昵称")
    private String nickName;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "标签")
    private Integer tagId;

    @Schema(description = "开始时间")
    private String startDate;

    @Schema(description = "结束时间")
    private String endDate;
}
