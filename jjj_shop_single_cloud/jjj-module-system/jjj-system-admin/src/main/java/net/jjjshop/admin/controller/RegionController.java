

package net.jjjshop.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.admin.param.RegionPageParam;
import net.jjjshop.admin.param.RegionParam;
import net.jjjshop.admin.service.RegionService;
import net.jjjshop.common.cache.RegionCache;
import net.jjjshop.common.vo.RegionVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.log.annotation.OperationLog;
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
@Tag(name = "admin-地区设置")
@RestController
@RequestMapping("/admin/region")
public class RegionController {

    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionCache regionCache;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Map<String,Object>> index(@Validated @RequestBody RegionPageParam regionPageParam) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("list", regionService.getList(regionPageParam));
        result.put("regionData", regionCache.getCacheTree());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer id) {
        if(regionService.setDelete(id)) {
            return ApiResult.ok(null, "删除成功");
        }else{
            return ApiResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<List<RegionVo>> toAdd() {
        return ApiResult.ok(regionCache.getCacheTree());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated RegionParam regionParam) {
        if(regionService.add(regionParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Map<String,Object>> toEdit(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("model", regionService.getEditData(id));
        result.put("regionData", regionCache.getCacheTree());
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated RegionParam regionParam) {
        if(regionService.edit(regionParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }
}
