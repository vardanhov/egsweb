package com.egswebapp.egsweb.configuration;


import com.egswebapp.egsweb.security.jwt.JwtConfigurer;
import com.egswebapp.egsweb.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.naming.ldap.PagedResultsControl;


/**
 * Security configuration class for JWT based Spring Security application.
 *
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;
    private final static String ADMIN_ENDPOINT = "/admin/**";
    private final static String LOGIN_ENDPOINT = "/users/login";
    private final static String REGISTER_ENDPOINT = "/users/register";
    private final static String CATEGORY_ENDPOINT = "/category/**";
    private final static String USER_AND_ADMIN_ENDPOINT = "users/update_password";
    private final static String CREATE_CATEGORY_ENDPOINT = "/category/create_category";
    private final static String SWAGGER_ENDPOINT = "swagger-ui.html/**";
    private final static String POST_ENDPOINT = "/posts/**";
    private final static String USER_ENDPOINT = "/users/**";
    private final static String PAGE_ENDPOINT = "/pages/**";
    private final  static String MENU_ENDPOINT="/menu/**";


    @Autowired
    public SpringSecurityConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT, REGISTER_ENDPOINT, SWAGGER_ENDPOINT).permitAll()
                .antMatchers(USER_AND_ADMIN_ENDPOINT, CREATE_CATEGORY_ENDPOINT, POST_ENDPOINT,USER_ENDPOINT,MENU_ENDPOINT).access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(ADMIN_ENDPOINT, CATEGORY_ENDPOINT,PAGE_ENDPOINT).access("hasRole('ADMIN')").anyRequest().fullyAuthenticated()
                .and()
                .apply(new JwtConfigurer(tokenProvider));



    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }


}
