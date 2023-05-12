package com.digital.tms.controller;

import com.digital.tms.model.dto.LoginDTO;
import com.digital.tms.model.dto.RegisterDTO;
import com.digital.tms.service.iServicies.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService service;


    record SimpleResponse(String status) {
    }
    record JwtResponse(String accessToken) {
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleResponse registerUser(@Valid @RequestBody RegisterDTO userDto) {
        service.register(userDto);
        return new SimpleResponse("user created");
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse login(@Valid @RequestBody LoginDTO loginDTO) {
        return new JwtResponse(service.login(loginDTO));
    }


}
