

package net.jjjshop.admin.service;

import net.jjjshop.common.entity.admin.AdminUser;
import net.jjjshop.common.param.LoginParam;
import net.jjjshop.framework.common.service.BaseService;

import jakarta.servlet.http.HttpServletRequest;
import net.jjjshop.admin.vo.LoginAdminUserTokenVo;

/**
 * 系统用户 服务类
 */
public interface AdminUserService extends BaseService<AdminUser> {
    /**
     * 登录
     *
     * @return
     */
    LoginAdminUserTokenVo login(LoginParam param);

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    Boolean renew(String password);

    /**
     * 退出
     *
     * @param request
     * @throws Exception
     */
    void logout(HttpServletRequest request) throws Exception;
}
