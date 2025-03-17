package net.jjjshop.common.settings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "系统设置VO")
public class SysConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String shopName;
    private String shopBgImg;
    private String shopLogoImg;
    //shop端登录验证码开关,true开,false关
    private Boolean loginCode;
    //admin端登录验证码开关,true开,false关
    private Boolean adminLoginCode;
    private WeixinService weixinService;
    //上传设置VO
    private StorageVo storageVo;

    public SysConfigVo() {
        this.shopName = "三勾商城管理系统";
        this.shopBgImg = "";
        this.shopLogoImg = "";
        this.loginCode = false;
        this.adminLoginCode = false;
        this.weixinService = new WeixinService();
        this.storageVo = new StorageVo();
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "系统设置VO")
    public static class WeixinService implements Serializable {
        private static final long serialVersionUID = 1L;
        private Boolean isOpen;
        private String appId;
        private String mchId;
        private String apiKey;
        private String certPem;
        private String keyPem;
        //微信支付验签类型,0证书,1公钥
        private Integer signType;
        //微信支付公钥文件Key
        private String publicKey;
        private String serialNo;

        public WeixinService() {
            this.isOpen = false;
            this.appId = "";
            this.mchId = "";
            this.apiKey = "";
            this.certPem = "";
            this.keyPem = "";
            this.serialNo = "";
            this.signType = 0;
            this.publicKey = "";
        }
    }
}
