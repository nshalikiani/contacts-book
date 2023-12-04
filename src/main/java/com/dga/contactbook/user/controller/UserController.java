package com.dga.contactbook.user.controller;

import com.dga.contactbook.user.request.AuthenticateUserRequest;
import com.dga.contactbook.user.request.RegisterUserRequest;
import com.dga.contactbook.user.response.AuthenticateUserResponse;
import com.dga.contactbook.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateUserResponse> authenticate(
            @RequestBody AuthenticateUserRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticateUserResponse> register(
            @RequestBody RegisterUserRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

}
