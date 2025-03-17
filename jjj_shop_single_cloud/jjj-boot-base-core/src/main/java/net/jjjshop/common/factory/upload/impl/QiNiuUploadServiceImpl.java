package net.jjjshop.common.factory.upload.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.factory.upload.UploadFactoryService;
import net.jjjshop.common.settings.vo.StorageVo;
import net.jjjshop.common.util.SettingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件七牛上传类
 */
@Slf4j
@Service
public class QiNiuUploadServiceImpl implements UploadFactoryService {
    @Lazy
    @Autowired
    private SettingUtils settingUtils;

    /**
     * 文件上传，返回文件路径
     *
     * @return
     */
    @Override
    public void upload(MultipartFile multipartFile, String saveFileName) throws Exception {
        Configuration cfg = new Configuration(Region.autoRegion());

        // 获取配置
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORAGE.getKey(), null);
        StorageVo storageVo = JSONObject.toJavaObject(vo, StorageVo.class);

        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(storageVo.getQiNiu().getAccessKey(), storageVo.getQiNiu().getSecretKey());
        String upToken = auth.uploadToken(storageVo.getQiNiu().getBucket());

        Response response = uploadManager.put(multipartFile.getBytes(), saveFileName, upToken);
        response.close();
    }
}
