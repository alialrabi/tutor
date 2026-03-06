package com.tutor.security;

import com.tutor.persistance.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AppUserDetails extends User {

    private final UserProfile user;

    public AppUserDetails(UserProfile user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassWord(), authorities);
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Long getTutorId() {
        // Assuming UserProfile has a getTutor() method that returns a Tutor object with an id
        if (user.getTutor() != null) {
            return user.getTutor().getId();
        }
        return null;
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
