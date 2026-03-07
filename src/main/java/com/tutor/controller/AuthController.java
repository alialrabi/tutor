package com.tutor.controller;

import com.tutor.common.dto.*;
import com.tutor.business.service.AuthService;
import com.tutor.controller.request.LoginRequest;
import com.tutor.controller.request.RegisterRequest;
import com.tutor.controller.response.AuthResponse;
import com.tutor.controller.response.UserProfileResponse;
import com.tutor.persistance.entity.UserProfile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public GenericResponseEntity<UserProfile> register(
            @Valid @RequestBody RegisterRequest request) {
        UserProfile response = authService.register(request);
        return GenericResponseEntity.generateResponse(response);
    }

    @PostMapping("/login")
    public GenericResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return GenericResponseEntity.generateResponse(response);
    }

    @GetMapping("/me")
    public GenericResponseEntity<UserProfileResponse> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse response = authService.getCurrentUser(userDetails.getUsername());
        return GenericResponseEntity.generateResponse(response);
    }
}
