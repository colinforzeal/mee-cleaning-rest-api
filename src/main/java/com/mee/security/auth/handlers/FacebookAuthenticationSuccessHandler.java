package com.mee.security.auth.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mee.entity.Role;
import com.mee.entity.User;
import com.mee.security.model.UserContext;
import com.mee.security.model.token.JwtToken;
import com.mee.security.model.token.JwtTokenFactory;
import com.mee.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FacebookAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;
    private JwtTokenFactory tokenFactory;
    private ObjectMapper mapper;

    @Autowired
    public FacebookAuthenticationSuccessHandler(UserService userService, JwtTokenFactory tokenFactory, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenFactory = tokenFactory;
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Map<String, String> details = (Map<String, String>) userAuthentication.getDetails();

        // todo check if exists
        User user = new User();
        user.setFacebookId(details.get("id"));
        user.setName(details.get("name"));
        user.setRoles(Arrays.asList(Role.ROLE_USER));
        userService.save(user);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        UserContext context = new UserContext(user.getFacebookId(), authorities);

        JwtToken accessToken = tokenFactory.createAccessJwtTokenForUser(context);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", accessToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);
    }
}
