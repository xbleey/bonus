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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/16 0016
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:}")
    private String jwkSetUri;

    @Bean
    /**
     * Build the application's security filter chain: URL access rules, login page, and logout behavior.
     * Supports both form-based authentication and OAuth2 JWT validation.
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure URL access rules, form login, and logout behavior for the application.
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/index", "/").permitAll()
                        .requestMatchers("/projects").hasRole("engineer")
                        .requestMatchers("/engineer/**").hasRole("engineer")
                        .requestMatchers("/pm/**").hasRole("pm")
                        .requestMatchers("/director/**").hasRole("director")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index")
                );

        // Enable OAuth2 Resource Server JWT validation if jwk-set-uri is configured
        if (jwkSetUri != null && !jwkSetUri.isEmpty()) {
            http.oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(Customizer.withDefaults())
            );
        }

        return http.build();
    }

    @Bean
    /**
     * Load users from LoginService and register them in an in-memory store for authentication.
     */
    public InMemoryUserDetailsManager userDetailsService(LoginService loginService) {
        // Initialize users from LoginService and register them in an in-memory store for authentication.
        loginService.init();
        List<UserDetails> users = new ArrayList<>();

        for (UserInfo userInfo : loginService.getUserInfos()) {
            UserDetails user = User.builder()
                    .username(userInfo.getUserName())
                    .password(passwordEncoder().encode(userInfo.getPassWord()))
                    .roles(userInfo.getRole())
                    .build();
            users.add(user);
        }

        loginService.destroy();
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
 

