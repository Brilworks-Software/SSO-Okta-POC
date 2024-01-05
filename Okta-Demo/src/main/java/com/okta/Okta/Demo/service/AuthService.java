
package com.okta.Okta.Demo.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.okta.Okta.Demo.dto.SSOAuthenticationDTO;
import com.okta.Okta.Demo.dto.SSOConfigurationDto;
import com.okta.Okta.Demo.dto.SSOUserDTO;
import com.okta.Okta.Demo.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {


    @Value("${okta.oauth2.issuer}")
    String issuerUrl;

    @Value("${okta.oauth2.client-id}")
    String clientId;

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public SSOUserDTO validateTokenAndGetSSOUser(SSOAuthenticationDTO ssoAuthenticationDTO) {
        try {
            SSOConfigurationDto ssoConfiguration = getSsoConfiguration();
            if (ssoConfiguration != null && ssoAuthenticationDTO.getAccessToken() != null) {
                return getOrCreateUserFromSSO(ssoAuthenticationDTO.getAccessToken(), ssoAuthenticationDTO.getEmail(), ssoConfiguration.getIssuerUrl());
            } else {
                throw new ResourceNotFoundException("SSO Configuration not found!");
            }
        } catch (Exception e) {
            log.debug("Exception while validating sso token {}", e.getMessage());
            throw new ResourceNotFoundException("User not found!");
        }
    }

    public SSOConfigurationDto getSsoConfiguration() {
        return new SSOConfigurationDto(clientId,issuerUrl);
    }

    private SSOUserDTO getOrCreateUserFromSSO(String accessToken, String email, String url) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(url + "/v1/userinfo")
                    .header("Authorization", "Bearer " + accessToken)
                    .asJson();
            if (response != null && response.getStatus() == 200) {
                JSONObject responseObj = response.getBody().getObject();
                if (responseObj != null && responseObj.getBoolean("email_verified")) {
                    String userEmail = responseObj.getString("email");
                    String fName = responseObj.getString("given_name");
                    String lName = responseObj.getString("family_name");
                    SSOUserDTO ssoUserDTO = new SSOUserDTO(fName, lName, userEmail);
                    if(email.equalsIgnoreCase(userEmail))
                        return userService.getOrCreateUserFromSSOUser(ssoUserDTO);
                }
            }
        } catch (Exception e) {
            log.debug("Exception while validating sso token {}", e.getMessage());
            throw new ResourceNotFoundException("User not found!");
        }
        return null;
    }

}