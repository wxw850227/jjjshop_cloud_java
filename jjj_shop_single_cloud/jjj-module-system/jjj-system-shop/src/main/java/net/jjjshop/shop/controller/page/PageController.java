

package net.jjjshop.shop.controller.page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.util.PageUtils;
import net.jjjshop.common.util.diy.DefaultItems;
import net.jjjshop.config.properties.SpringBootJjjProperties;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.CommonPageParam;
import net.jjjshop.shop.service.page.PageService;
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
@Tag(name = "shop-页面")
@RestController
@RequestMapping("/shop/page/page")
public class PageController {
    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/list")
    @OperationLog(name = "list")
    @Operation(summary = "list")
    public ApiResult<Map<String,Object>> list(@Validated @RequestBody CommonPageParam commonPageParam) throws Exception{
        Map<String,Object> result = new HashMap<>();
        result.put("list", pageService.getList(commonPageParam, 10));
        result.put("default", pageService.getDefault());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    @RequiresPermissions("/page/page/editPage")
    @OperationLog(name = "editPage")
    @Operation(summary = "editPage")
    public ApiResult<Map<String,Object>> toEditPage(Integer pageId) throws Exception{
        Map<String,Object> result = new HashMap<>();
        result.put("jsonData", pageService.detail(pageId));
        result.put("defaultData", DefaultItems.getDefaultItems(SpringBootJjjProperties.getStaticAccessUrl()));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/editPage", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/editPage")
    @OperationLog(name = "editPage")
    @Operation(summary = "editPage")
    public ApiResult<String> editPage(Integer pageId, String params) throws Exception{
        if(pageService.edit(pageId, params)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/setPage", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/setPage")
    @OperationLog(name = "setPage")
    @Operation(summary = "setPage")
    public ApiResult<String> setPage(Integer pageId) throws Exception{
        if(pageService.setDefaultPage(pageId)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    @RequiresPermissions("/page/page/addPage")
    @OperationLog(name = "addPage")
    @Operation(summary = "addPage")
    public ApiResult<Map<String,Object>> toAddPage() throws Exception{
        Map<String,Object> result = new HashMap<>();
        result.put("jsonData", PageUtils.getDefaultPage());
        result.put("defaultData", DefaultItems.getDefaultItems(SpringBootJjjProperties.getStaticAccessUrl()));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/addPage")
    @OperationLog(name = "addPage")
    @Operation(summary = "addPage")
    public ApiResult<String> addPage(String params) throws Exception{
        if(pageService.add(10, params)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer pageId) throws Exception{
        if(pageService.setDelete(pageId)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String,Object>> index(@Validated @RequestBody CommonPageParam commonPageParam) throws Exception{
        Map<String,Object> result = new HashMap<>();
        result.put("list", pageService.getList(commonPageParam, 20));;
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @RequiresPermissions("/page/page/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Map<String,Object>> toAdd() throws Exception{
        Map<String,Object> result = new HashMap<>();
        JSONObject jsonData = new JSONObject();
        jsonData.put("page", PageUtils.getDefaultPage());
        jsonData.put("items", new JSONArray());
        result.put("jsonData", jsonData);
        result.put("defaultData", DefaultItems.getDefaultItems(SpringBootJjjProperties.getStaticAccessUrl()));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(String params) throws Exception{
        if(pageService.add(20, params)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @RequiresPermissions("/page/page/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Map<String,Object>> toEdit(Integer pageId) throws Exception{
        Map<String,Object> result = new HashMap<>();
        result.put("jsonData", pageService.detail(pageId).getPageDataJson());
        result.put("defaultData", DefaultItems.getDefaultItems(SpringBootJjjProperties.getStaticAccessUrl()));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/page/page/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(Integer pageId, String params) throws Exception{
        if(pageService.edit(pageId, params)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }
}
