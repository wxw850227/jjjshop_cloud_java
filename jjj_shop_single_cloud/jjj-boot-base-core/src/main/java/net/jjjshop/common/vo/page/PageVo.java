

package net.jjjshop.common.vo.page;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;
import net.jjjshop.common.entity.page.Page;

@Data
@Accessors(chain = true)
@Schema(description = "页面VO")
public class PageVo extends Page {

    private static final long serialVersionUID = 1L;

    @Schema(description = "页面数据，json格式")
    private JSONObject pageDataJson;
}
