package com.semihbkgr.springboot.oauth.resource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.mvcMatcher("/item/**")
                .authorizeRequests()
                .mvcMatchers("/item/**")
                .access("hasAuthority('SCOPE_item.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
    }

}
