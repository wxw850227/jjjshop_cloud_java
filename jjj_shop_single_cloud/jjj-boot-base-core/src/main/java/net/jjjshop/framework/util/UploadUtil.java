

package net.jjjshop.framework.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.file.UploadFile;
import net.jjjshop.common.enums.SettingEnum;
import net.jjjshop.common.enums.StorageEnum;
import net.jjjshop.common.factory.upload.UploadFactory;
import net.jjjshop.common.factory.upload.UploadFactoryService;
import net.jjjshop.common.settings.vo.StorageVo;
import net.jjjshop.common.util.SettingUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件上传工具类
 */
@Slf4j
@Component
public class UploadUtil {
    @Autowired
    private SettingUtils settingUtils;
    @Autowired
    private UploadFactory uploadFactory;

    /**
     * 上传文件
     *
     * @param multipartFile
     */
    public synchronized void upload(MultipartFile multipartFile, UploadFile file) throws Exception {
        // 获取配置
        JSONObject vo = settingUtils.getSetting(SettingEnum.STORAGE.getKey(), null);
        StorageVo storageVo = JSONObject.toJavaObject(vo, StorageVo.class);

        UploadFactoryService service = uploadFactory.getService(storageVo.getCurrent());
        // 保存文件名
        String originalFilename = multipartFile.getOriginalFilename();
        String saveFileName = new DefaultUploadFileNameHandleImpl().handle(originalFilename);
        // 开始上传
        service.upload(multipartFile, saveFileName);
        file.setFileName(saveFileName);
        file.setStorage(storageVo.getCurrent());
        if (StorageEnum.QINIU.getValue().equals(storageVo.getCurrent())) {
            file.setFileUrl(storageVo.getQiNiu().getDomain());
        } else if (StorageEnum.ALIYUN.getValue().equals(storageVo.getCurrent())) {
            file.setFileUrl(storageVo.getAliYun().getDomain());
        } else if (StorageEnum.QCLOUD.getValue().equals(storageVo.getCurrent())) {
            file.setFileUrl(storageVo.getQCloud().getDomain());
        }else if (StorageEnum.MINIO.getValue().equals(storageVo.getCurrent())) {
            file.setFileUrl(storageVo.getMinIo().getDomain() + "/" + storageVo.getMinIo().getBucket());
        }
    }


    public interface UploadFileNameHandle {
        /**
         * 回调处理接口
         *
         * @param originalFilename
         * @return
         */
        String handle(String originalFilename);
    }

    public class DefaultUploadFileNameHandleImpl implements UploadFileNameHandle {

        @Override
        public String handle(String originalFilename) {
            // 文件后缀
            String fileExtension = FilenameUtils.getExtension(originalFilename);
            // 这里可自定义文件名称，比如按照业务类型/文件格式/日期
            // 此处按照文件日期存储
            String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

            return dateString + "." + fileExtension;
        }
    }
}
