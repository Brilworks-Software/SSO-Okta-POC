package com.okta.Okta.Demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.okta.Okta.Demo.dto.SSOUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("EMAIL")
    @Column(unique = true)
    private String email;

    @JsonProperty("FIRST_NAME")
    private String firstName;

    @JsonIgnore
    private String password;

    @JsonProperty("LAST_NAME")
    private String lastName;

    public User(SSOUserDTO userDTO){
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getUserEmail();
        this.password = userDTO.getPassword();
    }

}