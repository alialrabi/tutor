package com.tutor.business.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tutor.controller.response.AuthResponse;
import com.tutor.enums.Provider;
import com.tutor.persistance.entity.UserProfile;
import com.tutor.persistance.repository.UserProfileRepository;
import com.tutor.security.AppUserDetails;
import com.tutor.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;
import java.security.AuthProvider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserProfileRepository userRepository;
    private final JwtService jwtService;
    private final GoogleIdTokenVerifier googleIdTokenVerifier; // ✅ injected as bean
    private final UserDetailsService userDetailsService;

    public AuthResponse authenticateWithGoogle(String idToken) throws AuthenticationException {
        // 1. Verify
        GoogleIdToken.Payload payload = verifyToken(idToken);

        // 2. Check email is verified by Google
        if (!payload.getEmailVerified()) {
            throw new AuthenticationException("Email not verified");
        }

        // 3. Find or create user — thread safe
        String email = payload.getEmail();

        UserProfile user = userRepository.findByEmail(email).orElseGet(() -> createGoogleUser(payload));

        if (user.getProvider() == Provider.LOCAL) {
            throw new AuthenticationException(
                    "This email is already registered with a password. Please login normally."
            );
        }

        userRepository.save(user);

        List<String> permissions = loadPermissions(user.getId());

        List<GrantedAuthority> authorities = loadPermissions(user.getId())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        AppUserDetails userDetails = new AppUserDetails(user, authorities);

        String token = jwtService.generateToken(userDetails, permissions);

        user.setJwtToken(token);

        return buildAuthResponse(token, user);
    }

    public List<String> loadPermissions(Long userId) {
        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> permissions = user.getRoles().stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());

        return permissions;
    }


    private AuthResponse buildAuthResponse(String token, UserProfile user) {
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }


    private GoogleIdToken.Payload verifyToken(String idToken) {
        try {
            GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(idToken);
            if (googleIdToken == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Google token");
            }
            return googleIdToken.getPayload();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Google token verification failed");
        }
    }

    private synchronized UserProfile createGoogleUser(GoogleIdToken.Payload payload) {
        UserProfile user = new UserProfile();
        user.setEmail(payload.getEmail());
        user.setFirstName((String) payload.get("given_name"));
        user.setLastName((String) payload.get("family_name"));
        user.setProvider(Provider.GOOGLE);
        user.setEnabled(true);
        return user;
    }
}