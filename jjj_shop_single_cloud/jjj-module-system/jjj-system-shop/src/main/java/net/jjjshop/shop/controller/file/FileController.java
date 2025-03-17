

package net.jjjshop.shop.controller.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.file.UploadGroup;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.file.UploadFilePageParam;
import net.jjjshop.shop.service.file.UploadFileService;
import net.jjjshop.shop.service.file.UploadGroupService;
import net.jjjshop.shop.vo.file.UploadFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "shop-文件管理")
@RestController
@RequestMapping("/shop/file/file")
public class FileController {
    @Autowired
    private UploadGroupService uploadGroupService;
    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @OperationLog(name = "category")
    @Operation(summary = "category")
    public ApiResult<List<UploadGroup>> category(@RequestParam String type) throws Exception{
        return ApiResult.ok(uploadGroupService.getAll(type));
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    @OperationLog(name = "addGroup")
    @Operation(summary = "addGroup")
    public ApiResult<String> addGroup(@RequestParam String groupName, @RequestParam String groupType) throws Exception{
        if(uploadGroupService.addGroup(groupName, groupType)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/editGroup", method = RequestMethod.POST)
    @OperationLog(name = "editGroup")
    @Operation(summary = "editGroup")
    public ApiResult<String> editGroup(Integer groupId, String groupName) throws Exception{
        if(uploadGroupService.editGroup(groupId, groupName)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    @OperationLog(name = "deleteGroup")
    @Operation(summary = "deleteGroup")
    public ApiResult<String> deleteGroup(Integer groupId) throws Exception{
        if(uploadGroupService.deleteGroup(groupId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
    @OperationLog(name = "deleteFiles")
    @Operation(summary = "deleteFiles")
    public ApiResult<String> deleteFiles(String fileIds) throws Exception{
        if(uploadGroupService.deleteFiles(fileIds)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<Paging<UploadFileVo>> lists(@Validated @RequestBody UploadFilePageParam uploadFilePageParam) throws Exception{
        return ApiResult.ok(uploadFileService.getList(uploadFilePageParam));
    }

    @RequestMapping(value = "/moveFiles", method = RequestMethod.POST)
    @OperationLog(name = "moveFiles")
    @Operation(summary = "moveFiles")
    public ApiResult<String> moveFiles(Integer groupId, String fileIds) throws Exception{
        if(uploadGroupService.moveFiles(groupId, fileIds)) {
            return ApiResult.ok(null, "移动成功");
        }else{
            return ApiResult.fail("移动失败");
        }
    }
}
