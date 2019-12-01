package com.silvia.securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @Author: Silvia
 * @Date: 2019/11/9  13:50
 */



//@Service
public class UserService {
    /*
    @Autowired
    private DataSource dataSource;
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(  );
        manager.setDataSource( dataSource );
        //manager.createUser( User.withUsername("user").password( "123" ).roles( "USER" ).build() );
        //manager.createUser( User.withUsername( "admin" ).password( "123" ).roles( "USER" ).build() );
        return manager;
    }

     */
}


/*

@Service
public class UserService {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(  );
        manager.createUser( User.withUsername("user").password( "123" ).roles( "USER" ).build() );
        manager.createUser( User.withUsername( "admin" ).password( "123" ).roles( "USER" ).build() );
        return manager;
    }
}
 */