package com.dga.contactbook.user.service;

import com.dga.contactbook.config.JwtUtils;
import com.dga.contactbook.user.UserEntity;
import com.dga.contactbook.user.UserRole;
import com.dga.contactbook.user.repository.UserRepository;
import com.dga.contactbook.user.request.AuthenticateUserRequest;
import com.dga.contactbook.user.request.RegisterUserRequest;
import com.dga.contactbook.user.response.AuthenticateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticateUserResponse authenticate(AuthenticateUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserEntity user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtUtils.generateToken(user);
        return AuthenticateUserResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticateUserResponse register(RegisterUserRequest request) {

        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        repository.save(user);
        String jwtToken = jwtUtils.generateToken(user);
        return AuthenticateUserResponse.builder()
                .token(jwtToken)
                .build();
    }

}
