package com.tutor.controller;

import com.tutor.business.service.GoogleAuthService;
import com.tutor.common.dto.*;
import com.tutor.business.service.AuthService;
import com.tutor.controller.request.GoogleTokenRequest;
import com.tutor.controller.request.LoginRequest;
import com.tutor.controller.request.RegisterRequest;
import com.tutor.controller.response.AuthResponse;
import com.tutor.controller.response.UserProfileResponse;
import com.tutor.persistance.entity.UserProfile;
import com.tutor.security.AppUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public GenericResponseEntity<UserProfileResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        UserProfileResponse response = authService.register(request);
        return GenericResponseEntity.generateResponse(response);
    }

    @PostMapping(value = "/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseEntity<?> uploadPhoto(
            @RequestPart("file") MultipartFile file,
            @RequestPart("email") String email
        ) throws IOException {
        return GenericResponseEntity.generateResponse(authService.uploadPhoto(email, file));
    }

    @Autowired
    private GoogleAuthService googleAuthService;


    @PostMapping("/login")
    public GenericResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return GenericResponseEntity.generateResponse(response);
    }

    @PostMapping("/google")
    public GenericResponseEntity<AuthResponse> loginWithGoogle(
            @RequestBody GoogleTokenRequest request) throws AuthenticationException {
        AuthResponse response = googleAuthService.authenticateWithGoogle(request.getIdToken());
        return GenericResponseEntity.generateResponse(response);
    }

    @GetMapping("/me")
    public GenericResponseEntity<UserProfileResponse> getCurrentUser(
            @AuthenticationPrincipal AppUserDetails userDetails) {
        UserProfileResponse response = authService.getCurrentUser(userDetails.getUsername());
        return GenericResponseEntity.generateResponse(response);
    }
}
