package com.itmuch.cloud.authorities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private static final String[] AUTH_WHITELIST = {
        // -- register ur
        "/users/signup",
         "/wxLogin",
        // -- swagger ui
        "/com/wap/**",
        "/api/com/pad/**",
        "/com/pad/**",
        "/wap/**/**.js",
        "/wap/css/**.css",
        "/pc/**/**.js",
        "/pc/**/**.js.map",
        "/pc/**/**.css",
        "/pc/**/**.css.map",
        "/pad/**/**.js",
        "/pad/**/**.js.map",
        "/pad/**/**.css",
        "/pad/**/**.css.map",
        "/v2/api-docs",
        "/api/com/wap/**",
        "/static/imgs/**",
        "/static/img/**",
        "/static/fonts/**",
        "/jhd/**",
        "/operationFile/**",
        "/favicon.ico",
        "/swagger-resources",
        "/swagger-resources/**",
        "/test",
        "/",
        "/wx/**",
        "/api/login",
        "/dist",
        "/wap",
        "/pad",
        "/loginHtml",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "**/*.js",
        "/about.html",
        "/content.html",
        "/joinus.html",
        "/news.html",
        "/*.txt",
        "/product.html",
        "/lianxi.html",
        "/jhd/**"
};

private UserDetailsService userDetailsService;

private BCryptPasswordEncoder bCryptPasswordEncoder;

public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
}

// 设置 HTTP 验证规则
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()  
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest().authenticated()  // 所有请求需要身份认证                
        .and()
        .addFilter(new JWTLoginFilter(authenticationManager()))
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .logout() // 默认注销行为为logout，可以通过下面的方式来修改
        .logoutUrl("/api/logout")
        .logoutSuccessUrl("/loginHtml")
        .permitAll();// 设置注销成功后跳转页面，默认是跳转到登录页面;
        
}

// 该方法是登录的时候会进入
@Override
public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 使用自定义身份验证组件
    auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder));
}
}
