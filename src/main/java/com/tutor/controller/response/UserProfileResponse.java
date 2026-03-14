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
    private java.util.List<String> roles;
    private java.util.List<String> permissions;
    private byte[] image;
}

