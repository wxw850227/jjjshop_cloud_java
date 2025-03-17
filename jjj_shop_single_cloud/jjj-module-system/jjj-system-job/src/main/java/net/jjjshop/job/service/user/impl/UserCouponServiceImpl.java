package net.jjjshop.job.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.plus.coupon.UserCoupon;
import net.jjjshop.common.mapper.user.UserCouponMapper;
import net.jjjshop.framework.common.service.impl.BaseServiceImpl;
import net.jjjshop.job.service.user.UserCouponService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户优惠券记录表 服务实现类
 * @author jjjshop
 * @since 2022-08-02
 */
@Slf4j
@Service
public class UserCouponServiceImpl extends BaseServiceImpl<UserCouponMapper, UserCoupon> implements UserCouponService {

    /**
     * 获取已过期的优惠券ID集
     */
    @Override
    public List<Integer> getExpiredCouponIds(){
        LambdaQueryWrapper<UserCoupon> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCoupon::getIsExpire, 0);
        wrapper.eq(UserCoupon::getIsUse, 0);
        wrapper.lt(UserCoupon::getEndTime, new Date());
        return this.list(wrapper).stream().map(UserCoupon::getUserCouponId).collect(Collectors.toList());
    }

    /**
     * 设置优惠券过期状态
     */
    @Override
    public Boolean setIsExpire(List<Integer> couponIds){
        if(CollectionUtil.isEmpty(couponIds)){
            return false;
        }
        return this.update(new LambdaUpdateWrapper<UserCoupon>().in(UserCoupon::getUserCouponId,couponIds).set(UserCoupon::getIsExpire, 1));
    }
}
