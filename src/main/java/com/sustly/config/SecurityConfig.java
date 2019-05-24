package com.sustly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author liyue
 * @date 2019/5/22 9:32
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义配置
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*都可访问*/
                .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()
                /*需要相应的角色*/
                .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                /*基于表单验证*/
                .formLogin()
                .loginPage("/login")
                .failureUrl("/error");
    }

    /**
     * 认证信息管理
     * @param auth auth
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
    }
}
