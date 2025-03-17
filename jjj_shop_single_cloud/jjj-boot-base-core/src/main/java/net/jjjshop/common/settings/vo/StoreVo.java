package net.jjjshop.common.settings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.enums.DeliveryTypeEnum;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "系统设置VO")
public class StoreVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Integer> deliveryType;
    private Boolean isGetLog;
    //h5注册是否开启短信验证
    private Boolean smsOpen;
    //小程序登录授权开关
    private Boolean wxOpen;
    //小程序绑定授权开关
    private Boolean wxBinding;
    //是否自动向微信小程序发货
    private Boolean isSendWx;
    //H5登录模式：0手机号登录，1公众号登录
    private Integer mpState;
    private KuaiDi100 kuaiDi100;
    private Policy policy;
    /**
     * 公众号登录中转地址
     */
    private String mpOauthUrl;

    /**
     * 是否启用公众号登录中转
     */
    private Boolean enableMpOauth;

    //会员默认头像
    private String avatarUrl;
    //小程序是否开启手机号登录
    private Boolean wxPhone;
    //会员默认昵称
    private String userName;
    // 登录logo
    private String loginLogo;
    // 登录描述
    private String loginDesc;
    // 动画设置
    private String loadImage;
    //平台logo
    private String logoUrl;
    //公众号绑定授权开关
    private Boolean mpBinding;

    public StoreVo() {
        this.name = "玖玖珈商城";
        this.deliveryType = DeliveryTypeEnum.getValues();
        this.isGetLog = false;
        this.smsOpen = false;
        this.wxOpen = true;
        this.wxBinding = false;
        this.mpBinding = true;
        this.isSendWx = false;
        this.mpState = 1;
        this.kuaiDi100 = new KuaiDi100();
        this.policy = new Policy();
        this.enableMpOauth = false;
        this.mpOauthUrl = "";
        this.avatarUrl = "https://food.jjjshop.net/image/user/avatarUrl.png";
        this.wxPhone = false;
        this.userName = "会员";
        this.loginLogo = "https://qn-cdn.jjjshop.net/202406141508488595d5235.png";
        this.loginDesc = "成为会员，立享更多优惠福利";
        this.loadImage = "https://qn-cdn.jjjshop.net/202406271418224e2f47505.gif";
        this.logoUrl = "https://qn-cdn.jjjshop.net/202312281002582d3303945.png";
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "快递100VO")
    public static class KuaiDi100 implements Serializable {
        private static final long serialVersionUID = 1L;
        //授权key
        private String key;
        //查询customer
        private String customer;
        //电子面单secret
        private String secret;

        public KuaiDi100() {
            this.customer = "";
            this.key = "";
            this.secret = "";
        }
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "隐私政策")
    public static class Policy implements Serializable {
        private static final long serialVersionUID = 1L;
        private String service;
        private String privacy;

        public Policy() {
            this.service = "";
            this.privacy = "";
        }
    }
}
