package net.jjjshop.common.factory.upload.impl;

import com.alibaba.fastjson.JSONObject;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.factory.upload.UploadFactoryService;
import net.jjjshop.common.settings.vo.StorageVo;
import net.jjjshop.common.util.SettingUtils;
import net.jjjshop.framework.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * MinIo上传类
 */
@Slf4j
@Service
public class MinIoUploadServiceImpl implements UploadFactoryService {
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
        // 获取配置
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORAGE.getKey(), null);
        StorageVo storageVo = JSONObject.toJavaObject(vo, StorageVo.class);

        MinioClient minioClient = null;
        try {
            minioClient = MinioClient.builder()
                    .endpoint(storageVo.getMinIo().getDomain())
                    .credentials(storageVo.getMinIo().getAccessKey(), storageVo.getMinIo().getSecretKey())
                    .build();
            InputStream stream = multipartFile.getInputStream();
            PutObjectArgs objectArgs = PutObjectArgs.builder().object(saveFileName)
                    .bucket(storageVo.getMinIo().getBucket())
                    .contentType(multipartFile.getContentType())
                    .stream(stream,multipartFile.getSize(),-1).build();
            minioClient.putObject(objectArgs);
            stream.close();
        } catch (Exception e) {
            throw new BusinessException("MinIo上传失败:" +e.getMessage());
        }
    }
}
