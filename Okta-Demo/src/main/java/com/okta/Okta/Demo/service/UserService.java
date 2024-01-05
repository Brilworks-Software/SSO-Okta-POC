package com.okta.Okta.Demo.service;

import com.okta.Okta.Demo.domain.User;
import com.okta.Okta.Demo.dto.SSOUserDTO;
import com.okta.Okta.Demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SSOUserDTO getOrCreateUserFromSSOUser(SSOUserDTO ssoUserDTO){

        User user = userRepository.findUserByEmailIgnoreCase(ssoUserDTO.getUserEmail())
                .orElseGet(() -> {
                    User newUser = new User(ssoUserDTO);
                    userRepository.save(newUser);
                    return newUser;
                });

        return new SSOUserDTO(user);

    }

}