package net.jjjshop.front.vo.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "门店VO")
public class StoreVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "门店id")
    private Integer storeId;

    @Schema(description = "门店名称")
    private String storeName;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "logo图片")
    private String logoImage;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "地区")
    private String region;

    @Schema(description = "距离")
    private Double distinct;

    @Schema(description = "显示距离")
    private String showDistinct;

    @Schema(description = "门店坐标经度")
    private String longitude;

    @Schema(description = "门店坐标纬度")
    private String latitude;
}
