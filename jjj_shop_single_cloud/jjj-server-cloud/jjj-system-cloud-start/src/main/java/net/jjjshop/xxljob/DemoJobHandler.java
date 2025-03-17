
package net.jjjshop.xxljob;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * xxl-job定时任务测试
 * @author jjjshop
 * @date: 2024/04/21
 */
@Component
@Slf4j
public class DemoJobHandler {


    /**
     * 简单任务
     *
     * @return
     */
    @XxlJob(value = "demoJob")
    public ReturnT<String> demoJob() {
        String param = XxlJobHelper.getJobParam();
        log.info("我是 jjjSingle-system 服务里的定时任务 demoJob，我执行了...............................");
        if(StringUtils.isNotBlank(param)){
            log.info(param);
        }
        return ReturnT.SUCCESS;
    }

}

