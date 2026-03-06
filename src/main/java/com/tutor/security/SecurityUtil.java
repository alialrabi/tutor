package com.tutor.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof AppUserDetails userDetails) {
            return userDetails.getUserId();
        }

        throw new BadCredentialsException("Invalid authentication principal");
    }
}
