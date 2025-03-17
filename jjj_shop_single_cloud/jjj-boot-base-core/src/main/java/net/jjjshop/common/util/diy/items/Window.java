package net.jjjshop.common.util.diy.items;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.jjjshop.common.util.diy.DiyItem;

/**
 * 图片橱窗
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "window")
public class Window implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @Schema(description = "diyItem")
    private DiyItem item;

    public Window(String imagePath){
        this.item = new DiyItem();
        item.setName("图片橱窗");
        item.setType("window");
        item.setGroup("media");
        // 样式
        JSONObject style = new JSONObject();
        style.put("paddingTop", 0);
        style.put("paddingLeft", 0);
        style.put("background", "#ffffff");
        style.put("layout", 2);
        item.setStyle(style);

        // 参数
        JSONObject params = new JSONObject();
        params.put("dataNum", 4);
        item.setParams(params);


        // 4条数据
        JSONArray data = new JSONArray();
        JSONObject itemData1 = new JSONObject();
        itemData1.put("imgUrl", imagePath + "image/diy/window/01.jpg");
        itemData1.put("linkUrl", "");
        data.add(itemData1);
        JSONObject itemData2 = new JSONObject();
        itemData2.put("imgUrl", imagePath + "image/diy/window/02.jpg");
        itemData2.put("linkUrl", "");
        data.add(itemData2);
        JSONObject itemData3 = new JSONObject();
        itemData3.put("imgUrl", imagePath + "image/diy/window/03.jpg");
        itemData3.put("linkUrl", "");
        data.add(itemData3);
        JSONObject itemData4 = new JSONObject();
        itemData4.put("imgUrl", imagePath + "image/diy/window/04.jpg");
        itemData4.put("linkUrl", "");
        data.add(itemData4);
        item.setData(data);
    }
}
