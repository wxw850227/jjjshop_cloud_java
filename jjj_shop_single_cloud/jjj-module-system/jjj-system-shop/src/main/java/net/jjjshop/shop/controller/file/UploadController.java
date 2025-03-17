/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.jjjshop.shop.controller.file;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.file.UploadFile;
import net.jjjshop.common.util.UploadFileUtils;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.framework.util.ShopLoginUtil;
import net.jjjshop.framework.util.UploadUtil;
import net.jjjshop.shop.service.file.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/shop/file/upload")
@Tag(name = "shop-文件上传")
public class UploadController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @OperationLog(name = "上传单个图片")
    @Operation(summary = "上传单个图片")
    public ApiResult<String> upload(@RequestParam("iFile") MultipartFile multipartFile,
                                    @RequestParam Integer groupId, @RequestParam String fileType) throws Exception {
        // 保存文件到数据库
        UploadFile file = new UploadFile();
        file.setGroupId(groupId);
        file.setFileSize(multipartFile.getSize());
        file.setFileType(fileType);
        file.setRealName(multipartFile.getOriginalFilename());
        // 上传文件
        uploadUtil.upload(multipartFile, file);
        // 文件后缀
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        file.setExtension(extension);
        file.setTenantId(ShopLoginUtil.getTenantId());
        if(uploadFileService.addFile(file)) {
            String filePath =  uploadFileUtils.getFilePathByFile(file);
            return ApiResult.ok(filePath, "上传成功");
        }else{
            return ApiResult.fail("上传失败");
        }
    }

}
