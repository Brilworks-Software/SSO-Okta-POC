package com.okta.Okta.Demo.controller;

import com.okta.Okta.Demo.service.TechService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
@AllArgsConstructor
public class TechController {

    TechService techService;

    @GetMapping(value = "technologies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok().body(
                techService.getTechnologies());
    }
}
