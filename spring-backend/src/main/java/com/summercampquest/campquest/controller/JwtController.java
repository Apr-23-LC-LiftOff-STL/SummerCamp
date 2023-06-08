package com.summercampquest.campquest.controller;

import com.summercampquest.campquest.models.JwtRequest;
import com.summercampquest.campquest.models.JwtResponse;
import com.summercampquest.campquest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        return jwtService.createJwtToken(jwtRequest);

    }
}
