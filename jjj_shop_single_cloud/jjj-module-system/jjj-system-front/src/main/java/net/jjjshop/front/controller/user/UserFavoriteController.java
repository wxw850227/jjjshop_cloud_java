package net.jjjshop.front.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.user.User;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.front.controller.BaseController;
import net.jjjshop.front.param.user.UserFavoritePageParam;
import net.jjjshop.front.service.user.UserFavoriteService;
import net.jjjshop.front.vo.user.UserFavoriteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "front-用户收藏")
@RestController
@RequestMapping("/front/user/favorite")
public class UserFavoriteController extends BaseController {

    @Autowired
    private UserFavoriteService userFavoriteService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @OperationLog(name = "list")
    @Operation(summary = "list")
    public ApiResult<Paging<UserFavoriteVo>> lists(@RequestBody UserFavoritePageParam userFavoritePageParam) {
        userFavoritePageParam.setUserId(this.getUser(true).getUserId());
        return ApiResult.ok(userFavoriteService.getList(userFavoritePageParam));
    }

    @RequestMapping(value = "/fav", method = RequestMethod.POST)
    @OperationLog(name = "fav")
    @Operation(summary = "fav")
    public ApiResult<String> fav(Integer productId) {
        User user = this.getUser(true);
        if(userFavoriteService.isFav(productId, user.getUserId())){
            if(userFavoriteService.delById(productId,this.getUser(true).getUserId())) {
                return ApiResult.ok(null, "操作成功");
            }else{
                return ApiResult.fail("操作失败");
            }
        }else{
            if(userFavoriteService.add(productId,this.getUser(true).getUserId())) {
                return ApiResult.ok(null, "收藏成功");
            }else{
                return ApiResult.fail("收藏失败");
            }
        }
    }

    @RequestMapping(value = "/cancelFav", method = RequestMethod.POST)
    @OperationLog(name = "cancelFav")
    @Operation(summary = "cancelFav")
    public ApiResult<String> cancelFav(Integer productId) {
        if(userFavoriteService.delById(productId,this.getUser(true).getUserId())) {
            return ApiResult.ok(null, "收藏成功");
        }else{
            return ApiResult.fail("收藏失败");
        }
    }
}
