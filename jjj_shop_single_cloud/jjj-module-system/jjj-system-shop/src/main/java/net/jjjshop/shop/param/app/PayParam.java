package net.jjjshop.shop.param.app;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "支付方式参数")
public class PayParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "支付类型，json格式")
    private JSONObject payType;

    @Schema(description = "微信支付验签类型,0证书,1公钥")
    private Integer wxSignType;

    @Schema(description = "微信支付公钥文件Key")
    private String pubKeyPem;

    @Schema(description = "微信支付方式v2：2,v3：3")
    private Integer wxPayKind;

    @Schema(description = "微信提现方式v2:2 v3:3")
    private Integer wxCashType;

    @Schema(description = "支付宝提现方式，1=手动打款， 2=审核通过自动转账")
    private Integer aliCashType;

    @Schema(description = "微信商户号id")
    private String mchid;

    @Schema(description = "微信支付密钥")
    private String apikey;

    @Schema(description = "证书文件cert")
    private String certPem;

    @Schema(description = "证书文件key")
    private String keyPem;

    @Schema(description = "微信平台证书序列号")
    private String wechatpaySerial;

    @Schema(description = "支付宝商户号")
    private String alipayAppid;

    @Schema(description = "支付宝公钥")
    private String alipayPublickey;

    @Schema(description = "应用私钥")
    private String alipayPrivatekey;
}
