package net.jjjshop.shop.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.user.Tag;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "标签Vo")
public class TagVo extends Tag {
    private static final long serialVersionUID = 1L;

    @Schema(description = "使用该标签的会员人数")
    private Long count;
}
