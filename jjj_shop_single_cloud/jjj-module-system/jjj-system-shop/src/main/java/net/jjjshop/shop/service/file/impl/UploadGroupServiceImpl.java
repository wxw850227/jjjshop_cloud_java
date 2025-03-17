package net.jjjshop.shop.service.file.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.file.UploadFile;
import net.jjjshop.common.entity.file.UploadGroup;
import net.jjjshop.common.mapper.file.UploadGroupMapper;
import net.jjjshop.framework.common.service.impl.BaseServiceImpl;
import net.jjjshop.shop.service.file.UploadFileService;
import net.jjjshop.shop.service.file.UploadGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件库分组记录表 服务实现类
 * @author jjjshop
 * @since 2022-06-28
 */
@Slf4j
@Service
public class UploadGroupServiceImpl extends BaseServiceImpl<UploadGroupMapper, UploadGroup> implements UploadGroupService {
    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 所有文件分组
     * @return
     */
    @Override
    public List<UploadGroup> getAll(String groupType){
        return this.list(new LambdaQueryWrapper<UploadGroup>().eq(UploadGroup::getGroupType, groupType)
                .eq(UploadGroup::getIsDelete, false)
                .orderByAsc(UploadGroup::getSort)
                .orderByAsc(UploadGroup::getCreateTime));
    }
    /**
     * 新增分组
     * @param groupName
     * @param groupType
     * @return
     */
    @Override
    public boolean addGroup(String groupName, String groupType){
        UploadGroup group = new UploadGroup();
        group.setGroupName(groupName);
        group.setGroupType(groupType);
        return this.save(group);
    }

    /**
     * 修改分组
     * @param groupId
     * @param groupName
     * @return
     */
    @Override
    public boolean editGroup(Integer groupId, String groupName){
        return this.update(new LambdaUpdateWrapper<UploadGroup>().eq(UploadGroup::getGroupId, groupId)
                .set(UploadGroup::getGroupName, groupName));
    }

    /**
     * 删除分组
     * @param groupId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteGroup(Integer groupId){
        // 更新该分组下的所有文件
        uploadFileService.update(new LambdaUpdateWrapper<UploadFile>().eq(UploadFile::getGroupId, groupId)
                .set(UploadFile::getGroupId, 0));
        // 删除分组
        this.update(new LambdaUpdateWrapper<UploadGroup>().eq(UploadGroup::getGroupId, groupId)
                .set(UploadGroup::getIsDelete, 1));
        return true;
    }

     /**
     * 删除文件
     * @param fileIds
     * @return
     */
     @Override
     public boolean deleteFiles(String fileIds){
        return uploadFileService.update(new LambdaUpdateWrapper<UploadFile>().in(UploadFile::getFileId, this.transFileIds(fileIds))
                .set(UploadFile::getIsDelete, 1));
     }

    /**
     * 移动文件
     * @param fileIds
     * @return
     */
    @Override
    public boolean moveFiles(Integer groupId, String fileIds){
        return uploadFileService.update(new LambdaUpdateWrapper<UploadFile>().in(UploadFile::getFileId, this.transFileIds(fileIds))
                .set(UploadFile::getGroupId, groupId));
    }

    private List<Integer> transFileIds(String fileIds) {
        String[] split = StringUtils.split(fileIds, ",");
        List<Integer> files = new ArrayList<>();
        for (String fileId : split) {
            files.add(Integer.parseInt(fileId));
        }
        return files;
    }
}
