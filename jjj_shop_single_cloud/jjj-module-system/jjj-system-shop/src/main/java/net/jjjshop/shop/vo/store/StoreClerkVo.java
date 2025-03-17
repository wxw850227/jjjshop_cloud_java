package net.jjjshop.shop.vo.store;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.store.StoreClerk;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "门店店员VO")
public class StoreClerkVo extends StoreClerk {

    private static final long serialVersionUID = 1L;

    @Schema(description = "微信昵称")
    private String nickName;

    @Schema(description = "微信头像")
    private String avatarUrl;

    @Schema(description = "店名")
    private String storeName;

    @Schema(description = "店员状态")
    private String statusText;
}
