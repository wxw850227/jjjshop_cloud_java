package net.jjjshop.common.settings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Schema(description = "引导收藏VO")
public class CollectionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;

    public CollectionVo() {
        this.status = "0";
    }
}
