package com.silvia.securitydemo.beans;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collection;
import java.util.List;

/**
 * @Author: Silvia
 * @Date: 2019/11/9  16:39
 */
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String roles;
    private boolean enable;

    public User(Long id, String username, String password, int enable, String roles ) {
        this.id = id;
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode( password );
        this.roles = roles;
        if (enable == 1) {
            this.enable = true;
        } else {
            this.enable = false;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder(  ).encode( password );
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    private List<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

}
