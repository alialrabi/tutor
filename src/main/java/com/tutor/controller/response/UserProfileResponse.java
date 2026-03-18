package com.tutor.controller.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long status;
    private java.util.Set<String> roles;
    private java.util.Set<String> permissions;
    private byte[] image;
    private Long tutorId;
}

