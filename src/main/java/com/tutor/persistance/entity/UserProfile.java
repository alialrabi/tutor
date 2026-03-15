package com.tutor.persistance.entity;

import com.tutor.common.ProviderConverter;
import com.tutor.common.UserTypeConverter;
import com.tutor.enums.Provider;
import com.tutor.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.security.AuthProvider;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile", schema = "tutor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", schema = "tutor", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "pass_word", nullable = true)
    private String passWord;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private Long status = 0L;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        schema = "tutor",
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "jwt_token")
    private String jwtToken;


    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @OneToOne(mappedBy = "userProfile")
    private Tutor tutor;

    @Enumerated(EnumType.STRING)
    @Convert(converter = UserTypeConverter.class)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Convert(converter = ProviderConverter.class)
    private Provider provider;
}
