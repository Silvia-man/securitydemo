package com.silvia.securitydemo.mapper;

import com.silvia.securitydemo.beans.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: Silvia
 * @Date: 2019/11/9  16:53
 */

@Component
public interface UserMapper {
    @Select( "select * from users where username=#{username}" )
    User findByUserName(@Param( "username" ) String username);
}
