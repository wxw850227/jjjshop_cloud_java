

package net.jjjshop.shop.param.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 部门 查询参数对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "ProductParam对象", description = "Product新增修改参数")
public class ProductParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "产品id")
    private Integer productId;

    @NotBlank(message = "产品名称不能为空")
    @Schema(description = "产品名称")
    private String productName;

    @Schema(description = "产品一口价")
    private BigDecimal productPrice;

    @Schema(description = "产品编码")
    private String productNo;

    @Schema(description = "产品总库存")
    private Integer productStock;

    @Schema(description = "视频id")
    private Integer videoId;

    @Schema(description = "视频封面id")
    private Integer posterId;

    @Schema(description = "商品卖点")
    private String sellingPoint;

    @Schema(description = "产品分类id")
    private Integer categoryId;

    @Schema(description = "产品规格(10单规格 20多规格)")
    private Integer specType;

    @Schema(description = "产品规格(10单规格 20多规格)")
    private Boolean isSpecLocked;

    @Schema(description = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    @Schema(description = "产品详情")
    private String content;

    @Schema(description = "初始销量")
    private Integer salesInitial;

    @Schema(description = "实际销量")
    private Integer salesActual;

    @Schema(description = "产品排序(数字越小越靠前)")
    private Integer productSort;

    @Schema(description = "配送模板id")
    private Integer deliveryId;

    @Schema(description = "是否开启积分赠送(1开启 0关闭)")
    private Integer isPointsGift;

    @Schema(description = "是否允许使用积分抵扣(1允许 0不允许)")
    private Integer isPointsDiscount;

    @Schema(description = "最大积分抵扣数量")
    private Integer maxPointsDiscount;

    @Schema(description = "是否开启会员折扣(1开启 0关闭)")
    private Integer isEnableGrade;

    @Schema(description = "会员折扣设置(0默认等级折扣 1单独设置折扣)")
    private Integer isAloneGrade;

    @Schema(description = "单独设置折扣的配置")
    private String aloneGradeEquity;

    @Schema(description = "折扣金额类型(10百分比 20固定金额)")
    private Integer aloneGradeType;

    @Schema(description = "产品状态(10上架 20仓库中 30回收站)")
    private Integer productStatus;

    @Schema(description = "是否虚拟，0否1是")
    private Integer isVirtual;

    @Schema(description = "限购数量0为不限")
    private Integer limitNum;

    @Schema(description = "可购买会员等级id，逗号隔开")
    private String gradeIds;

    @Schema(description = "是否自动发货1自动0手动")
    private Integer virtualAuto;

    @Schema(description = "虚拟物品内容")
    private String virtualContent;

    @Schema(description = "详情是否纯图0否1是")
    private Integer isPicture;

    @Schema(description = "详情视频id")
    private Integer contentVideoId;

    @Schema(description = "详情视频封面id")
    private Integer contentPosterId;

    @Schema(description = "关联表单id")
    private Integer tableId;

    @NotNull(message = "图片集合id不能为空")
    @Schema(description = "图片集合id")
    private List<ImageParam> image;

    @Schema(description = "内容图片集合id")
    private List<ImageParam> contentImage;

    @Schema(description = "商品单规格sku属性")
    private SkuParam sku;

    @Schema(description = "场景，编辑还是复制")
    private String scene;

    @Schema(description = "多规格")
    private JSONObject specMany;

    @Schema(description = "预告商品1是0否")
    private Integer isPreview;

    @Schema(description = "预告时间")
    private Date previewTime;

    @Schema(description = "自定义表单数组")
    private JSONArray customForm;

    @Data
    @Accessors(chain = true)
    @Schema(description = "上传图片VO")
    public static class ImageParam implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer imageId;
        private String filePath;

        public ImageParam() {

        }
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "商品SKU")
    public static class SkuParam implements Serializable {
        private static final long serialVersionUID = 1L;
        private BigDecimal linePrice;
        private String productNo;
        private BigDecimal productPrice;
        private Double productWeight;
        private Integer stockNum;

        public SkuParam() {

        }
    }
}
