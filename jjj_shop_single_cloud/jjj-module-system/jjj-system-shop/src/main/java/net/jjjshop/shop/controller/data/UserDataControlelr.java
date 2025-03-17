package net.jjjshop.shop.controller.data;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.UserGrade;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.user.UserPageParam;
import net.jjjshop.shop.service.user.UserGradeService;
import net.jjjshop.shop.service.user.UserService;
import net.jjjshop.shop.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "shop-查询用户")
@RestController
@RequestMapping("/shop/data/user")
public class UserDataControlelr {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGradeService userGradeService;

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    @OperationLog(name = "lists")
    @Operation(summary = "lists")
    public ApiResult<Map<String, Object>> index(@Validated @RequestBody UserPageParam userPageParam) throws Exception{
        Map<String, Object> map = new HashMap<>();
        List<UserGrade> grades = userGradeService.getAll();
        Paging<UserVo> users = userService.getList(userPageParam);
        map.put("grades", grades);
        map.put("users",users);
        return ApiResult.ok(map);
    }
}
