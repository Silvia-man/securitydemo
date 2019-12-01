package com.silvia.securitydemo.configuration;

import com.silvia.securitydemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Silvia
 * @Date: 2019/11/8  16:02
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure( HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //ANT模式的URL匹配器。ANT模式使用？匹配任意单个字符，使用*匹配0或任意数量的字符，使用**匹配0或者更多的目录。
                // 这里相当于匹配了admin/api/下的所有API.
                //HTTP状态码 2XX表示本次操作成功， 4XX表示客户端导致的失败，5XX表示服务器引起的错误
                //401用户认证失败，403用户授权失败

                .antMatchers( "/admin/api/**" ).hasRole( "dianchangguanli" )
                .antMatchers( "/user/api/**" ).hasRole( "USER" )
                .antMatchers( "/app/api/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage( "/myLogin.html" ).permitAll()
                .and()
                .csrf().disable();
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

       auth.userDetailsService( myUserDetailsService ).passwordEncoder( passwordEncoder() );
/*
        auth.inMemoryAuthentication() //认证信息存储到内存中
               .passwordEncoder(passwordEncoder())
                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
 */
    }

    private PasswordEncoder passwordEncoder() {
      //  return new MyPasswordEncoder();
        return new BCryptPasswordEncoder();
    }



    public class MyPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.equals(charSequence.toString());
        }
    }

}


/*

//第一版
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage( "/myLogin.html" )
                    .successHandler( new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType( "application/json;charset=UTF-8" );
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write("{\"error_code\":\"0\",\"message\":\"欢迎登录系统\"}");
                        }
                    } )
                    .failureHandler( new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType( "application/json;charset=UTF-8" );
                            httpServletResponse.setStatus( 401 );
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write( "{\"error_code\":\"401\", \"name\":\"" + e.getClass() +
                                    "\", \"message\":\"" +e.getMessage() + "\"}" );
                        }
                } )
                .and()
                .csrf().disable();
    }
}

 */
