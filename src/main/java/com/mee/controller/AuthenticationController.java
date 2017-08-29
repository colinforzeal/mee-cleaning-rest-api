package com.mee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mee.entity.Company;
import com.mee.entity.Role;
import com.mee.security.auth.AuthRequest;
import com.mee.security.exceptions.AuthMethodNotSupportedException;
import com.mee.security.model.CompanyContext;
import com.mee.security.model.token.AccessJwtToken;
import com.mee.security.model.token.JwtToken;
import com.mee.security.model.token.JwtTokenFactory;
import com.mee.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// todo validation
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtTokenFactory tokenFactory;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<JwtToken> signup(@RequestBody AuthRequest authRequest) {
        Company company = new Company();
        company.setEmail(authRequest.getEmail());
        company.setPassword(authRequest.getPassword());
        company.setRoles(Arrays.asList(Role.ROLE_COMPANY));

        companyService.save(company);

        List<GrantedAuthority> authorities = company.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        CompanyContext companyContext = new CompanyContext(company.getEmail(), authorities);

        JwtToken accessToken = tokenFactory.createAccessJwtToken(companyContext);

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
