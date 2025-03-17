package net.jjjshop.shop.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.UserGrade;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.user.UserGradePageParam;
import net.jjjshop.shop.param.user.UserGradeParam;
import net.jjjshop.shop.service.user.UserGradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "shop-会员等级")
@RestController
@RequestMapping("/shop/user/grade")
public class UserGradeController {

    @Autowired
    private UserGradeService userGradeService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/user/grade/delete")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<UserGrade>> index(@Validated @RequestBody UserGradePageParam userGradePageParam) throws Exception {
        return ApiResult.ok(userGradeService.getList(userGradePageParam));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/user/grade/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@Validated @RequestBody UserGradeParam userGradeParam) throws Exception {
        if (userGradeService.add(userGradeParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/user/grade/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@Validated @RequestBody UserGradeParam userGradeParam) throws Exception {
        if (userGradeService.edit(userGradeParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/user/grade/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult setDelete(Integer id) {
        if (userGradeService.setDelete(id)) {
            return ApiResult.ok("删除成功");
        } else {
            return ApiResult.fail("删除失败");
        }
    }


}
