package com.tutor.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.function.Function;

public class SecurityUtil {

    public static <T> T getCurrentData(Function<AppUserDetails, T> resolver) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof AppUserDetails userDetails) {
            return resolver.apply(userDetails);
        }

        throw new BadCredentialsException("Invalid authentication principal");
    }

}
