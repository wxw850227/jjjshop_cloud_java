package net.jjjshop.common.entity.app;

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
 * app应用记录表
 *
 * @author jjjshop
 * @since 2022-07-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AppOpen对象")
public class AppOpen implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "tenantId")
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Integer tenantId;

    @Schema(description = "应用AppID")
    private String openappId;

    @Schema(description = "应用AppSecret")
    private String openappSecret;

    @Schema(description = "微信商户号id")
    private String mchid;

    @Schema(description = "微信支付密钥")
    private String apikey;

    @Schema(description = "logo地址")
    private String logo;

    @Schema(description = "证书文件cert")
    private String certPem;

    @Schema(description = "证书文件key")
    private String keyPem;

    @Schema(description = "是否支持支付宝支付,0否1是")
    private Integer isAlipay;

    @Schema(description = "支付宝商户号")
    private String alipayAppid;

    @Schema(description = "支付宝公钥")
    private String alipayPublickey;

    @Schema(description = "应用私钥")
    private String alipayPrivatekey;

    @Schema(description = "是否回收")
    private Integer isRecycle;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
