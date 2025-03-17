package net.jjjshop.shop.param.store;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(name = "StoreParam对象", description = "StoreParam对象")
public class StoreParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "门店id")
    private Integer storeId;

    @Schema(description = "门店名称")
    private String storeName;

    @Schema(description = "门店logo图片id")
    private Integer logoImageId;

    @Schema(description = "联系人")
    private String linkman;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "营业时间")
    private String shopHours;

    @Schema(description = "所在省份id")
    private Integer provinceId;

    @Schema(description = "所在城市id")
    private Integer cityId;

    @Schema(description = "所在辖区id")
    private Integer regionId;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "门店坐标经度")
    private String longitude;

    @Schema(description = "门店坐标纬度")
    private String latitude;

    @Schema(description = "geohash")
    private String geohash;

    @Schema(description = "门店简介")
    private String summary;

    @Schema(description = "门店排序(数字越小越靠前)")
    private Integer sort;

    @Schema(description = "是否支持自提核销(0否 1支持)")
    private Integer isCheck;

    @Schema(description = "门店状态(0禁用 1启用)")
    private Integer status;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "小程序id")
    private Integer tenantId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "地址坐标")
    private String coordinate;

}
