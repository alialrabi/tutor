package com.tutor.business.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationMangerImpl implements AuthenticationManager {

    private final List<AuthenticationProvider> providers = new ArrayList<>();

    public AuthenticationMangerImpl(List<AuthenticationProvider> providerList) {
        // Spring will inject all AuthenticationProvider beans here
        providers.addAll(providerList);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        for (AuthenticationProvider provider: providers) {
            if (provider.supports(authentication.getClass())) {
                System.out.println(authentication.getClass());
                return provider.authenticate(authentication);
            }
        }
        throw new AuthenticationException("No suitable Provider found") {
			private static final long serialVersionUID = 1L;
		};
    }
}
