package com.okta.Okta.Demo.controller;

import com.okta.Okta.Demo.dto.SSOAuthenticationDTO;
import com.okta.Okta.Demo.dto.SSOUserDTO;
import com.okta.Okta.Demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(path = {"/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody SSOAuthenticationDTO ssoAuthenticationDTO) {
        try {
            SSOUserDTO ssoUserDTO = authService.validateTokenAndGetSSOUser(ssoAuthenticationDTO);
            if(Objects.nonNull(ssoUserDTO))
                return ResponseEntity.status(HttpStatus.OK)
                    .body(ssoUserDTO);
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not valid!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}