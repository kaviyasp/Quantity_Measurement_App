package com.bridgelabz.controller;

import com.bridgelabz.dto.AuthRequestDTO;
import com.bridgelabz.dto.AuthResponseDTO;
import com.bridgelabz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(
            @RequestBody AuthRequestDTO request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthRequestDTO request
    ) {

        return authService.login(request);
    }
}