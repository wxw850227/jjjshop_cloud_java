package net.jjjshop.front.param.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@Schema(description = "CategoryParam对象")
public class UserAddressParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    @Schema(description = "电话号码")
    private String phone;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "省份Id")
    private Integer provinceId;

    @Schema(description = "城市Id")
    private Integer cityId;

    @Schema(description = "区域Id")
    private Integer regionId;


}
