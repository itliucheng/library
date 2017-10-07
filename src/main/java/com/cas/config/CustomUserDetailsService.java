package com.cas.config;

/**
 * Created by wangliucheng on 2017/9/19 0019.
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 */

import com.cas.Dao.LdapDao;
import com.cas.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Autowired
    private LdapDao ldapDao;
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        String login = token.getPrincipal().toString();
        String username = login.toLowerCase();

        UserInfo userInfo = ldapDao.getUserDN("zhangsan");
        Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
        //设置权限
        AuthorityInfo authorityInfo = new AuthorityInfo(userInfo.getDescription());
        authorities.add(authorityInfo);
        userInfo.setAuthorities(authorities);
        return userInfo;
    }
}
