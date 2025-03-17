package net.jjjshop.common.entity.app;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.framework.core.validator.groups.Update;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信小程序记录表
 *
 * @author jjjshop
 * @since 2022-06-23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "App对象")
public class App implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "应用id")
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Integer tenantId;

    @Schema(description = "应用名称")
    private String appName;

    @Schema(description = "logo")
    private Integer logo;

    @Schema(description = "通行证是否开发0,不开放1,开放")
    private Integer passportOpen;

    @Schema(description = "通行证类型10,微信开放平台,20手机号30,账号密码")
    private Integer passportType;

    @Schema(description = "是否回收")
    private Boolean isRecycle;

    @Schema(description = "过期时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date expireTime;

    @Schema(description = "支付类型，json格式")
    private String payType;

    @Schema(description = "微信支付方式v2：2,v3：3")
    private Integer wxPayKind;

    @Schema(description = "微信商户号id")
    private String mchid;

    @Schema(description = "微信支付密钥")
    private String apikey;

    @Schema(description = "p12证书")
    private byte[] p12;

    @Schema(description = "证书文件cert")
    private String certPem;

    @Schema(description = "证书文件key")
    private String keyPem;

    @Schema(description ="微信支付验签类型,0证书,1公钥")
    private Integer wxSignType;

    @Schema(description ="微信支付公钥文件Key")
    private String pubKeyPem ;

    @Schema(description = "微信平台证书序列号")
    private String wechatpaySerial;

    @Schema(description = "是否删除")
    private Boolean isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
