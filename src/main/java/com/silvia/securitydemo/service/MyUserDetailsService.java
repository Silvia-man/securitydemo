package com.silvia.securitydemo.service;

import com.silvia.securitydemo.beans.User;
import com.silvia.securitydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Silvia
 * @Date: 2019/11/9  16:55
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userMapper.findByUserName( username );
        if(user == null) {
            throw new UsernameNotFoundException( "用户不存在" );
        }

        user.setAuthorities( AuthorityUtils.commaSeparatedStringToAuthorityList( user.getRoles() ) );
        return user;
    }

    //未用
    private List<GrantedAuthority> generateAuthorities(String roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(  );
        String[] roleArray = roles.split(",");
        if (roles != null && !"".equals( roles )) {
            for (String role : roleArray) {
                authorities.add( new SimpleGrantedAuthority( role ) );
            }
        }
        return authorities;
    }
}
