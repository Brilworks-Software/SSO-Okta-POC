package com.okta.Okta.Demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Technology {

    private String id;
    private String technology;
    private String description;

}
