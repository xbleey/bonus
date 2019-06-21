/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MySecurityConfig
 * Author:   11580
 * Date:     2019/6/16 0016 21:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.config;

import com.xbleey.entity.UserInfo;
import com.xbleey.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/16 0016
 * @since 1.0.0
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginService loginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index", "/").permitAll();
        http.authorizeRequests() .antMatchers("/engineer/**").hasRole("engineer");
        http.authorizeRequests().antMatchers("/pm/**").hasRole("pm");

        http.formLogin().loginPage("/login");
        http.logout().logoutSuccessUrl("/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        loginService.init();
        for (UserInfo userInfo : loginService.getUserInfos()) {
                auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser(userInfo.getUserName()).password(new BCryptPasswordEncoder().encode(userInfo.getPassWord())).roles(userInfo.getRole());
        }
        loginService.destroy();

    }
}
 

