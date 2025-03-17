package net.jjjshop.common.settings.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "上传设置VO")
public class StorageVo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 当前设置
    private String current;
    private QiNiu qiNiu;
    private AliYun aliYun;
    private QCloud qCloud;
    private MinIo minIo;

    public StorageVo() {
        this.current = "Local";
        this.qiNiu = new QiNiu();
        this.aliYun = new AliYun();
        this.qCloud = new QCloud();
        this.minIo = new MinIo();
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "MinIo设置VO")
    public static class MinIo implements Serializable {
        private static final long serialVersionUID = 1L;
        private String domain;
        private String accessKey;
        private String secretKey;
        private String bucket;

        public MinIo() {
            this.bucket = "";
            this.accessKey = "";
            this.secretKey = "";
            this.domain = "http://";
        }
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "七牛设置VO")
    public static class QiNiu implements Serializable {
        private static final long serialVersionUID = 1L;
        private String bucket;
        private String accessKey;
        private String secretKey;
        private String domain;

        public QiNiu() {
            this.bucket = "";
            this.accessKey = "";
            this.secretKey = "";
            this.domain = "http://";
        }
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "阿里云设置VO")
    public static class AliYun implements Serializable {
        private static final long serialVersionUID = 1L;
        private String bucket;
        private String accessKeyId;
        private String accessKeySecret;
        private String domain;
        private String endpoint;

        public AliYun() {
            this.bucket = "";
            this.accessKeyId = "";
            this.accessKeySecret = "";
            this.domain = "http://";
            this.endpoint = "";
        }
    }

    @Data
    @Accessors(chain = true)
    @Schema(description = "腾讯云设置VO")
    public static class QCloud implements Serializable {
        private static final long serialVersionUID = 1L;
        private String bucket;
        private String region;
        private String secretId;
        private String secretKey;
        private String domain;

        public QCloud() {
            this.bucket = "";
            this.region = "";
            this.secretId = "";
            this.secretKey = "";
            this.domain = "http://";
        }
    }

}
