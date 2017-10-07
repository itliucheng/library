package com.cas.interceptor;

import com.cas.config.AuthorityInfo;
import com.cas.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wangliucheng on 2017/10/6 0006.
 */
public class RoleInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(RoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("------preHandle------");
        //存放用户角色信息
        List list = new ArrayList<>();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String name = userInfo.getUid();
            request.setAttribute("name",name);
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
