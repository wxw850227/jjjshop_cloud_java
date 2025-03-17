

package net.jjjshop.shop.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.user.UserPageParam;
import net.jjjshop.shop.param.user.UserParam;
import net.jjjshop.shop.service.user.TagService;
import net.jjjshop.shop.service.user.UserGradeService;
import net.jjjshop.shop.service.user.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "shop-会员管理")
@RestController
@RequestMapping("/shop/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserGradeService userGradeService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/user/user/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String, Object>> index(@Validated @RequestBody UserPageParam userPageParam) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("userList", userService.getList(userPageParam));
        result.put("gradeList",userGradeService.getAll());
        result.put("tagList", tagService.getAll());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @RequiresPermissions("/user/user/recharge")
    @OperationLog(name = "recharge")
    @Operation(summary = "recharge")
    public ApiResult<Boolean> recharge(@Validated @RequestBody UserParam userParam) throws Exception {
        return ApiResult.ok(userService.recharge(userParam));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/user/user/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Boolean> edit(@Validated @RequestBody UserParam userParam) throws Exception {
        return ApiResult.ok(userService.updateGrade(userParam));
    }

    @RequestMapping(value = "/toEditTag", method = RequestMethod.GET)
    @OperationLog(name = "toEditTag")
    @Operation(summary = "toEditTag")
    public ApiResult<Map<String, Object>> toEditTag(Integer userId) throws Exception {
        return ApiResult.ok(userService.toEditTag(userId));
    }


    @RequestMapping(value = "/editTag", method = RequestMethod.POST)
    @RequiresPermissions("/user/user/tag")
    @OperationLog(name = "editTag")
    @Operation(summary = "editTag")
    public ApiResult<String> toEditTag(@Validated @RequestBody UserParam userParam) throws Exception {
        if(userService.editTag(userParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/user/user/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer userId) throws Exception {
        if(userService.setDelete(userId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }
}
