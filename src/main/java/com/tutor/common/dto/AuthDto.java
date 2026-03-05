package com.tutor.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class AuthDto {

    @Data
    public static class RegisterRequest {
        @NotBlank @Email
        private String email;

        @NotBlank @Size(min = 6, max = 100)
        private String password;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @NotBlank
        private String phoneNumber;

        private String role = "STUDENT"; // STUDENT or TUTOR
    }

    @Data
    public static class LoginRequest {
        @NotBlank @Email
        private String email;

        @NotBlank
        private String password;
    }

    @Data
    @lombok.Builder
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class AuthResponse {
        private String token;
        private String tokenType = "Bearer";
        private Long userId;
        private String email;
        private String firstName;
        private String lastName;
        private java.util.List<String> roles;
        private java.util.List<String> permissions;
    }

    @Data
    @lombok.Builder
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class UserProfileResponse {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Long status;
        private java.util.List<String> roles;
        private java.util.List<String> permissions;
    }
}
