package com.mee.controller;

import com.mee.common.EmptyJsonResponse;
import com.mee.entity.Company;
import com.mee.entity.Role;
import com.mee.security.auth.AuthRequest;
import com.mee.security.model.CompanyContext;
import com.mee.security.model.token.JwtToken;
import com.mee.security.model.token.JwtTokenFactory;
import com.mee.service.user.UserService;

import com.mee.service.company.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


// todo validation
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenFactory tokenFactory;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody AuthRequest authRequest) {
        Company company = new Company();
        company.setEmail(authRequest.getEmail());
        company.setPassword(encoder.encode(authRequest.getPassword()));
        company.setRoles(Arrays.asList(Role.ROLE_COMPANY));

        companyService.save(company);

        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public ResponseEntity<Principal> facebook(Principal principal) {
//        User user = new User();
//        // todo get info from principal
//        userService.save(user);
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
//                .collect(Collectors.toList());
//
//        UserContext userContext = new UserContext(user.getFacebookId(), authorities);
//
//        JwtToken accessToken = tokenFactory.createAccessJwtTokenForUser(userContext);
//
//        return new ResponseEntity<>(accessToken, HttpStatus.OK);
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
