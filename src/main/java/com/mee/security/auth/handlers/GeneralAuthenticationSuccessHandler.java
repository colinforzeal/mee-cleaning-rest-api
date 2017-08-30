package com.mee.security.auth.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mee.security.model.CompanyContext;
import com.mee.security.model.token.JwtToken;
import com.mee.security.model.token.JwtTokenFactory;

@Component
public class GeneralAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;

    @Autowired
    public GeneralAuthenticationSuccessHandler(ObjectMapper mapper, JwtTokenFactory tokenFactory) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        CompanyContext companyContext = (CompanyContext) authentication.getPrincipal();
        
        JwtToken accessToken = tokenFactory.createAccessJwtTokenForCompany(companyContext);
        
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", accessToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);
    }
}
