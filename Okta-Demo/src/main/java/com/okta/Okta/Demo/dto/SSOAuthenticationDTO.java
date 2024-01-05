package com.okta.Okta.Demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SSOAuthenticationDTO {

    @JsonProperty("ACCESS_TOKEN")
    String accessToken;

    @JsonProperty("EMAIL")
    String email;

}