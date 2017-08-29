package com.mee.security.config;

import java.util.Arrays;
import java.util.List;

import com.mee.security.CustomCorsFilter;
import com.mee.security.auth.login.LoginAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mee.security.RestAuthenticationEntryPoint;
import com.mee.security.auth.login.LoginAuthenticationProvider;
import com.mee.security.auth.jwt.JwtAuthenticationProvider;
import com.mee.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.mee.security.auth.jwt.SkipPathRequestMatcher;
import com.mee.security.auth.jwt.extractor.TokenExtractor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "Authorization";
    public static final String LOGIN_ENTRY_POINT = "/api/auth/login";
    public static final String SIGNUP_ENTRY_POINT = "/api/auth/signup";
    public static final String RESOURCES_ENTRY_POINT = "/api/**";
    
    @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired private AuthenticationFailureHandler failureHandler;
    @Autowired private AuthenticationSuccessHandler successHandler;
    @Autowired private LoginAuthenticationProvider loginAuthenticationProvider;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired private TokenExtractor tokenExtractor;
    
    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private ObjectMapper objectMapper;

    protected LoginAuthenticationProcessingFilter buildLoginProcessingFilter() throws Exception {
        LoginAuthenticationProcessingFilter filter = new LoginAuthenticationProcessingFilter(LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(LOGIN_ENTRY_POINT, SIGNUP_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, RESOURCES_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter 
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable() // We don't need CSRF for JWT based authentication
        .exceptionHandling()
        .authenticationEntryPoint(this.authenticationEntryPoint)
        
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
            .authorizeRequests()
                .antMatchers(LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(SIGNUP_ENTRY_POINT).permitAll()
        .and()
            .authorizeRequests()
                .antMatchers(RESOURCES_ENTRY_POINT).authenticated() // Protected API End-points
        .and()
            .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
