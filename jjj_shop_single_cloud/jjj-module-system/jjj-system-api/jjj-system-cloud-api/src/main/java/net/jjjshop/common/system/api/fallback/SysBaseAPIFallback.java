package net.jjjshop.common.system.api.fallback;

import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.api.dto.DataLogDTO;
import net.jjjshop.common.api.dto.OnlineAuthDTO;
import net.jjjshop.common.api.dto.message.*;

import net.jjjshop.common.api.dto.message.*;
import net.jjjshop.common.system.api.ISysBaseAPI;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 进入fallback的方法 检查是否token未设置
 * @author: jjjshop
 */
@Slf4j
public class SysBaseAPIFallback implements ISysBaseAPI {

    @Setter
    private Throwable cause;

}
