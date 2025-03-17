package net.jjjshop.front.vo.product;


import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "商品评价VO")
public class CommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评价id")
    private Integer commentId;

    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "评价内容")
    private String content;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "用户微信头像")
    private String avatarUrl;

    @Schema(description = "评分 (10好评 20中评 30差评)")
    private Integer score;

    @Schema(description = "微信昵称")
    private String nickName;

    @Schema(description = "评价图片")
    private List<String> image;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "评价用户")
    private JSONObject users;

}
