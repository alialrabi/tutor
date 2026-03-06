package com.tutor.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AppUserDetails extends User {

    private final Long userId;
    private final Long tutorId;
    private final String email;

    public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                           Long userId, Long tutorId, String email) {
        super(username, password, authorities);
        this.userId = userId;
        this.tutorId = tutorId;
        this.email = email;
    }
}
