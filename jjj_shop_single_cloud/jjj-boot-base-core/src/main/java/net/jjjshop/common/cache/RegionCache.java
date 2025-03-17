package net.jjjshop.common.cache;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.DeliveryRule;
import net.jjjshop.common.entity.settings.Region;
import net.jjjshop.common.mapper.settings.RegionMapper;
import net.jjjshop.common.settings.vo.DeliveryRuleVo;
import net.jjjshop.common.vo.RegionVo;
import net.jjjshop.common.vo.setting.DeliveryVo;
import net.jjjshop.config.constant.CommonRedisKey;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RegionCache {
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<RegionVo> getCacheAll(){
        Map<String,Object> map = getCache();
        return (List<RegionVo>)map.get("all");
    }

    public Integer getCacheCount(String type){
        if(type.equals("city")){
            return getCityCountCache();
        }
        return 0;
    }

    /**
     * 清理缓存
     */
    public void clearCache(){
        redisTemplate.delete(CommonRedisKey.REGION_DATA);
        redisTemplate.delete(CommonRedisKey.REGION_CITY_COUNT);
        redisTemplate.delete(CommonRedisKey.REGION_TREE);
    }

    //运费模板详情缓存
    public List<DeliveryRuleVo> getDeliveryDetailCache(DeliveryVo vo){
        Object object = redisTemplate.opsForValue().get(CommonRedisKey.REGION_DELIVERY_DETAIL + vo.getDeliveryId());
        if(object != null){
            return (List<DeliveryRuleVo>)object;
        }
        // 所有区域
        List<RegionVo> regionList = this.getCacheAll();
        List<DeliveryRuleVo> ruleList = new ArrayList<>();
        for (DeliveryRule rule:vo.getRule()) {
            List<String> citys = Arrays.asList(rule.getRegion().split(","));
            List<Integer> province = new ArrayList<>();
            for(String cityId:citys){
                RegionVo cityVo = this.getCityById(regionList, Integer.parseInt(cityId));
                if(cityVo == null){
                    continue;
                }
                if(!province.contains(cityVo.getPid())){
                    province.add(cityVo.getPid());
                }
            }
            DeliveryRuleVo ruleVo = new DeliveryRuleVo();
            BeanUtils.copyProperties(rule, ruleVo);
            ruleVo.setCitys(citys);
            ruleVo.setProvince(province);
            ruleList.add(ruleVo);
        }
        redisTemplate.opsForValue().set(CommonRedisKey.REGION_DELIVERY_DETAIL + vo.getDeliveryId(), ruleList);
        return ruleList;
    }

    /**
     * 朝招地区vo
     * @param regionList
     * @return
     */
    private RegionVo getCityById(List<RegionVo> regionList, Integer cityId){
        RegionVo bean = null;
        for(RegionVo vo:regionList) {
            if(vo.getId().intValue() == cityId.intValue()){
                bean = vo;
                break;
            }
        }
        return bean;
    }

    //地区市总数缓存
    public Integer getCityCountCache(){
        Object object = redisTemplate.opsForValue().get(CommonRedisKey.REGION_CITY_COUNT);
        if(object != null){
            return (Integer)object;
        }
        // 所有地区
        List<Region> allList = regionMapper.selectList(new LambdaQueryWrapper<Region>()
                .eq(Region::getIsDelete, false));
        Integer cityCount = this.getAreaCount(allList, 2);
        //城市总数
        redisTemplate.opsForValue().set(CommonRedisKey.REGION_CITY_COUNT, cityCount);

        return cityCount;
    }

    public List<RegionVo> getCacheTree(){
        Object object = redisTemplate.opsForValue().get(CommonRedisKey.REGION_TREE);
        if(object != null){
            return (List<RegionVo>)object;
        }
        // 所有地区
        List<Region> allList = regionMapper.selectList(new LambdaQueryWrapper<Region>()
                .eq(Region::getIsDelete, false));
        // 树形结构
        // 转成vo
        List<RegionVo> voList = allList.stream().map(e -> {
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(e, regionVo);
            return regionVo;
        }).collect(Collectors.toList());
        // 遍历成树形结构
        List<RegionVo> collect = voList.stream()
                // 2. 找出所有顶级（规定 0 为顶级）
                .filter(o -> StrUtil.equals("0", String.valueOf(o.getPid())))
                // 3.给当前父级的 childList 设置子
                .peek(o -> o.setChildren(getChildList(o, voList)))
                // 4.收集
                .collect(Collectors.toList());
        redisTemplate.opsForValue().set(CommonRedisKey.REGION_TREE, collect);
        return collect;
    }

    public Map<String,Object> getCache(){
        Object object = redisTemplate.opsForValue().get(CommonRedisKey.REGION_DATA);
        if(object != null){
            return (HashMap<String,Object>)object;
        }
        // 所有地区
        List<Region> allList = regionMapper.selectList(new LambdaQueryWrapper<Region>()
                .eq(Region::getIsDelete, false));
        Map<String, Object> result = new HashMap<String, Object>();
        // 树形结构
        // 转成vo
        List<RegionVo> voList = allList.stream().map(e -> {
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(e, regionVo);
            return regionVo;
        }).collect(Collectors.toList());
        result.put("all", voList);
        // 地区总数
        result.put("total", allList.size());
        // 省份总数
        result.put("provinceCount", this.getAreaCount(allList, 1));
        result.put("cityCount", this.getAreaCount(allList, 2));
        result.put("areaCount", this.getAreaCount(allList, 3));
        redisTemplate.opsForValue().set(CommonRedisKey.REGION_DATA, result);
        return result;
    }



    // 根据当前父类 找出子类， 并通过递归找出子类的子类
    private List<RegionVo> getChildList(RegionVo bean, List<RegionVo> voList) {
        return voList.stream()
                //筛选出父节点id == parentId 的所有对象 => list
                .filter(o -> StrUtil.equals(String.valueOf(bean.getId()), String.valueOf(o.getPid())))
                .peek(o -> o.setChildren(getChildList(o, voList)))
                .collect(Collectors.toList());
    }

    /**
     * 查找地区数量
     * @param allList
     * @param level
     * @return
     */
    private Integer getAreaCount(List<Region> allList, int level){
        Integer count = 0;
        for (Region item : allList) {
            if(item.getLevel().intValue() == level){
                count++;
            }
        }
        return count;
    }
}
