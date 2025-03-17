package net.jjjshop.admin.service;

import net.jjjshop.admin.param.AppPageParam;
import net.jjjshop.admin.param.AppParam;
import net.jjjshop.admin.vo.AppVo;
import net.jjjshop.common.entity.app.App;
import net.jjjshop.framework.common.service.BaseService;
import net.jjjshop.framework.core.pagination.Paging;

/**
 * 微信小程序记录表 服务类
 *
 * @author jjjshop
 * @since 2022-06-23
 */
public interface AppService extends BaseService<App> {
    /**
     * app列表
     * @param appPageParam
     * @return
     */
    Paging<AppVo> getList(AppPageParam appPageParam);

    /**
     * 修改状态
     * @param tenantId
     * @return
     */
    Boolean editStatusById(Integer tenantId);
    /**
     * 修改微信服务商支付状态
     * @param tenantId
     * @return
     */
    Boolean updateWxStatus(Integer tenantId);
    /**
     * 删除
     * @param tenantId
     * @return
     */
    Boolean setDelete(Integer tenantId);
    /**
     * 新增
     * @param appParam
     * @return
     */
    Boolean add(AppParam appParam);
    /**
     * 修改
     * @param appParam
     * @return
     */
    Boolean edit(AppParam appParam);
}
