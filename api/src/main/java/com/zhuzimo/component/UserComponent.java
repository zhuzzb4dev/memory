package com.zhuzimo.component;

import com.zhuzimo.APIConstant;
import com.zhuzimo.exception.LoginException;
import com.zhuzimo.dto.LoginCacheDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 用户组件
 *
 * @author t3
 * @date 2023/12/11
 */
@Component
public class UserComponent {

    /**
     * GET 请求
     *
     * @return {@link HttpServletRequest}
     */
    private HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new LoginException("获取登录用户信息失败");
        }
        return requestAttributes.getRequest();
    }

    /**
     * 获取登录缓存 DTO
     *
     * @return {@link LoginCacheDto}
     */
    public LoginCacheDto getLoginCacheDto() {
        HttpServletRequest request = getRequest();
        return (LoginCacheDto) request.getAttribute(APIConstant.LOGIN_CACHE_DTO);
    }
}
