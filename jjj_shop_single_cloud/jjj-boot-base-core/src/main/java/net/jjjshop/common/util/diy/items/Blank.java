package net.jjjshop.common.util.diy.items;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.util.diy.DiyItem;

/**
 * 辅助空白组件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "blank")
public class Blank implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @Schema(description = "diyItem")
    private DiyItem item;

    public Blank(){
        this.item = new DiyItem();
        item.setName("辅助空白");
        item.setType("blank");
        item.setGroup("tools");
        // 样式
        JSONObject style = new JSONObject();
        style.put("background", "#ffffff");
        style.put("height", 20);
        item.setStyle(style);

        // 参数
        JSONObject params = new JSONObject();
        item.setParams(params);

        // 默认数据
        JSONArray data = new JSONArray();
        item.setData(data);
    }
}
