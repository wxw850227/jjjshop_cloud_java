

package net.jjjshop.framework.common.exception;

import net.jjjshop.framework.common.api.ApiCode;

/**
 * 业务异常
 */
public class BusinessException extends SpringBootJjjException {
    private static final long serialVersionUID = -2303357122330162359L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(ApiCode apiCode) {
        super(apiCode);
    }

}
