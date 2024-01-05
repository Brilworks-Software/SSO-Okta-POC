package com.okta.Okta.Demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SSOConfigurationDto {

    String clientId;
    String issuerUrl;
}