package com.negi.controller;

import com.negi.payload.dtos.UserDTO;
import com.negi.payload.request.LoginRequest;
import com.negi.payload.response.AuthResponse;
import com.negi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
for loginRequest --> LoginRequest(in login)                            (location : common-lib/payload/request)
AuthResponse work as LoginResponse/ SignUpResponse (both login/signup) (location : common-lib/payload/response)
UserDTO work as SignUpRequest(in signup)                               (location : common-lib/payload/dtos)
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid UserDTO userDTO) throws  Exception
    {
        AuthResponse response = authService.signUp(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest request) throws  Exception
    {
        AuthResponse response = authService.logIn(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(response);
    }
}
