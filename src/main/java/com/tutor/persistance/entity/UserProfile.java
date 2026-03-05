package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "public.users_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "pass_word", nullable = false)
    private String passWord;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Long status = 0L;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        schema = "public",
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    @Column(name = "jwt_token")
    private String jwtToken;
}
