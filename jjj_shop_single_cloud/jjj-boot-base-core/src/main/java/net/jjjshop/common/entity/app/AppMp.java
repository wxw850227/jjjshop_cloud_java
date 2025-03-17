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
 * 微信公众号记录表
 *
 * @author jjjshop
 * @since 2022-07-27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AppMp对象")
public class AppMp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "tenantId")
    @TableId(value = "tenant_id", type = IdType.AUTO)
    private Integer tenantId;

    @Schema(description = "公众号AppID")
    private String mpappId;

    @Schema(description = "公众号AppSecret")
    private String mpappSecret;

    @Schema(description = "微信商户号id")
    private String mchid;

    @Schema(description = "微信支付密钥")
    private String apikey;

    @Schema(description = "证书文件cert")
    private String certPem;

    @Schema(description = "证书文件key")
    private String keyPem;

    @Schema(description = "是否回收")
    private Integer isRecycle;

    @Schema(description = "是否删除")
    private Integer isDelete;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
