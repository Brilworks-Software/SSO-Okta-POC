package com.okta.Okta.Demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.okta.Okta.Demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SSOUserDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FIRST_NAME")
    private String firstName;

    @JsonProperty("LAST_NAME")
    private String lastName;

    @JsonProperty("USER_EMAIL")
    private String userEmail;

    @JsonIgnore
    private String password;

    public SSOUserDTO(String firstName, String lastName, String userEmail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
    }

    public SSOUserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userEmail = user.getEmail();
    }
}