

package net.jjjshop.shop.controller.setting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.cache.RegionCache;
import net.jjjshop.common.entity.settings.DeliveryRule;
import net.jjjshop.common.vo.RegionVo;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.setting.DeliveryPageParam;
import net.jjjshop.shop.param.setting.DeliveryParam;
import net.jjjshop.shop.service.settings.DeliveryService;
import net.jjjshop.common.settings.vo.DeliveryRuleVo;
import net.jjjshop.common.vo.setting.DeliveryVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@Tag(name = "shop-运费模板")
@RestController
@RequestMapping("/shop/setting/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private RegionCache regionCache;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/setting/delivery/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<DeliveryVo>> index(@Validated @RequestBody DeliveryPageParam deliveryPageParam) throws Exception{
        return ApiResult.ok(deliveryService.getList(deliveryPageParam));
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    @RequiresPermissions("/setting/delivery/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<Map<String, Object>> toAdd() {
        Map<String, Object> result = new HashMap<>();
        // 获取所有地区
        result.put("regionData", regionCache.getCacheTree());
        // 地区总数
        result.put("cityCount", regionCache.getCacheCount("city"));
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/setting/delivery/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated DeliveryParam deliveryParam) {
        if(deliveryService.add(deliveryParam)) {
            return ApiResult.ok(null, "新增成功");
        }else{
            return ApiResult.fail("新增失败");
        }
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/setting/delivery/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Map<String, Object>> toEdit(Integer deliveryId) {
        Map<String, Object> result = new HashMap<>();
        // 地区总数
        result.put("cityCount", regionCache.getCacheCount("city"));
        // 详情
        DeliveryVo detail = deliveryService.getDetail(deliveryId);
        result.put("detail", detail);
        // 格式化
        result.put("formData", regionCache.getDeliveryDetailCache(detail));
        // 获取所有地区
        List<RegionVo> regionData = regionCache.getCacheTree();
        // 初始选中
        this.checkedRegion(regionData, (List<DeliveryRuleVo>)result.get("formData"));
        result.put("regionData", regionData);
        return ApiResult.ok(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/setting/delivery/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated DeliveryParam deliveryParam) {
        if(deliveryService.edit(deliveryParam)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/setting/delivery/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult<String> delete(Integer deliveryId) {
        if(deliveryService.delById(deliveryId)) {
            return ApiResult.ok(null, "修改成功");
        }else{
            return ApiResult.fail("修改失败");
        }
    }

    /**
     * 修改时是否选中
     */
    private void checkedRegion(List<RegionVo> regionData, List<DeliveryRuleVo> formData){
        for(RegionVo regionVo:regionData){
            regionVo.setIndex(new ArrayList<Integer>());
            int index = 0;
            for(DeliveryRuleVo ruleVo:formData){
                // 是否选中
                if(ruleVo.getProvince().contains(regionVo.getId())){
                    regionVo.setChecked(true);
                }else{
                    regionVo.setChecked(false);
                }
                regionVo.getIndex().add(index);
                for(RegionVo child:regionVo.getChildren()){
                    // 是否选中
                    if(ruleVo.getCitys().contains(child.getId().toString())){
                        child.setChecked(true);
                        child.setChildIndex(index);
                    }else{
                        child.setChecked(false);
                        child.setChildIndex(-1);
                    }
                }
                index++;
            }
        }
    }
}
