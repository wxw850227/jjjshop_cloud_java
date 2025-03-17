package net.jjjshop.front.vo.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "文章VO")
public class UserAddressVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer addressId;

    @Schema(description = "新市辖区(该字段用于记录region表中没有的市辖区)")
    private String district;

    @Schema(description = "收货人姓名")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "所在省份id")
    private Integer provinceId;

    @Schema(description = "所在城市id")
    private Integer cityId;

    @Schema(description = "所在区id")
    private Integer regionId;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "详细地区")
    private JSONObject region;
}
