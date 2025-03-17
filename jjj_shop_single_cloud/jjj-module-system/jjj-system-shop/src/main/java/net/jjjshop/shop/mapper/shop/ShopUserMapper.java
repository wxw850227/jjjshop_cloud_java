package net.jjjshop.shop.mapper.shop;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.jjjshop.common.entity.shop.ShopAccess;
import net.jjjshop.common.entity.shop.ShopUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商家用户记录表 Mapper 接口
 *
 * @author jjjshop
 * @since 2022-06-23
 */
@Repository
public interface ShopUserMapper extends BaseMapper<ShopUser> {
    @InterceptorIgnore(tenantLine = "true")
    List<ShopAccess> getAccessByUserId(Integer userId);

    @InterceptorIgnore(tenantLine = "true")
    ShopUser selectByName(String username);

    @InterceptorIgnore(tenantLine = "true")
    long selectCountByName(String userName);
}
