package com.cas.controller;

import com.cas.config.AuthorityInfo;
import com.cas.model.RoleEnum;
import com.cas.model.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

import static com.cas.model.RoleEnum.STUDENT;

/**
 * Created by wangliucheng on 2017/9/19 0019.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {

        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String name = userInfo.getUid();
        String role = null;
        Set<AuthorityInfo> authorities = userInfo.getAuthorities();
        if(authorities.iterator().hasNext()){
            AuthorityInfo next = authorities.iterator().next();
            role = next.getAuthority();
        }
        request.setAttribute("role",role);
        request.setAttribute("name",name);
        return "index";
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('student')")//有TEST权限的才能访问
    @RequestMapping("/security")
    @ResponseBody
    public String security() {
        return "hello world security";
    }

    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问
    @RequestMapping("/authorize")
    @ResponseBody
    public String authorize() {
        return "有权限访问";
    }
}
