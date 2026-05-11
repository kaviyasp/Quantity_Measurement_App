package com.bridgelabz.service;

import com.bridgelabz.dto.AuthRequestDTO;
import com.bridgelabz.dto.AuthResponseDTO;
import com.bridgelabz.entity.Role;
import com.bridgelabz.entity.User;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(
            AuthRequestDTO request
    ) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String jwtToken =
                jwtService.generateToken(
                        new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                java.util.List.of()
                        )
                );

        return new AuthResponseDTO(jwtToken);
    }

    public AuthResponseDTO login(
            AuthRequestDTO request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user =
                userRepository.findByEmail(
                        request.getEmail()
                ).orElseThrow();

        String jwtToken =
                jwtService.generateToken(
                        new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                java.util.List.of()
                        )
                );

        return new AuthResponseDTO(jwtToken);
    }
}