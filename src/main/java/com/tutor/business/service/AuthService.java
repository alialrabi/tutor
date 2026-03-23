package com.tutor.business.service;

import com.tutor.controller.request.RegisterRequest;
import com.tutor.controller.response.AuthResponse;
import com.tutor.controller.request.LoginRequest;
import com.tutor.controller.response.UserProfileResponse;
import com.tutor.enums.Provider;
import com.tutor.enums.Roles;
import com.tutor.exception.DataIntegrityException;
import com.tutor.exception.EntityNotFoundException;
import com.tutor.persistance.entity.Permission;
import com.tutor.persistance.entity.Role;
import com.tutor.persistance.entity.UserProfile;
import com.tutor.persistance.repository.RoleRepository;
import com.tutor.persistance.repository.UserProfileRepository;
import com.tutor.security.AppUserDetails;
import com.tutor.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserProfileRepository userProfileRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Transactional(rollbackFor = IOException.class)
    public String uploadPhoto(String email, MultipartFile file) throws IOException {
        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setImage(file.getBytes());
        userProfileRepository.save(user);
        return "Success";
    }


    @Transactional
    public UserProfileResponse register(RegisterRequest request) {
        UserProfile user = UserProfile.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .status(0L)
                .userType(request.getUserType())
                .provider(Provider.LOCAL)
                .build();

        userProfileRepository.save(user);


        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .image(user.getImage())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserProfile user = userProfileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(user.getEmail());
        List<String> permissions = loadPermissions(user.getId());

        String token = jwtService.generateToken(userDetails, permissions);

        user.setJwtToken(token);

        return buildAuthResponse(token, user);
    }

    public List<String> loadPermissions(Long userId) {
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> permissions = user.getRoles().stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());

        return permissions;
    }

    @Transactional
    public UserProfileResponse getCurrentUser(String email) {
        UserProfile user = userProfileRepository.findByEmailWithRolesAndPermissionsAndTutor(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.toSet());

        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .image(user.getImage())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .roles(roles)
                .permissions(permissions)
                .tutorId(user.getTutor() != null ? user.getTutor().getId() : null)
                .build();
    }

    private AuthResponse buildAuthResponse(String token, UserProfile user) {
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }

    public void logout() {
        Object principal = (AppUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        if (principal instanceof AppUserDetails userDetails) {
            String email = userDetails.getEmail();
            UserProfile user = userProfileRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setJwtToken(null);
        }

        throw new BadCredentialsException("");
    }
}
