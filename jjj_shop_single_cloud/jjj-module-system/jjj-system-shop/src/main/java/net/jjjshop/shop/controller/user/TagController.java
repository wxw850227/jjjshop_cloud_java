package net.jjjshop.shop.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.user.TagPageParam;
import net.jjjshop.shop.param.user.TagParam;
import net.jjjshop.shop.service.user.TagService;
import net.jjjshop.shop.vo.user.TagVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-会员标签")
@RestController
@RequestMapping("/shop/user/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/user/tag/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<TagVo>> index(@Validated @RequestBody TagPageParam tagPageParam) throws Exception {
        return ApiResult.ok(tagService.getList(tagPageParam));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/user/tag/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@Validated @RequestBody TagParam tagParam) throws Exception {
        if (tagService.add(tagParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/user/tag/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@Validated @RequestBody TagParam tagParam) throws Exception {
        if (tagService.edit(tagParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/user/tag/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult delById(Integer id) {
        if (tagService.delById(id)) {
            return ApiResult.ok("删除成功");
        } else {
            return ApiResult.fail("删除失败");
        }
    }
}
