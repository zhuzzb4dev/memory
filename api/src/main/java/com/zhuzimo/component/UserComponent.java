package com.zhuzimo.component;

import com.zhuzimo.MemoryConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserComponent {

    public Long getLoginUserId() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return (Long) request.getAttribute(MemoryConstant.LOGIN_USER_ID);
    }
}
