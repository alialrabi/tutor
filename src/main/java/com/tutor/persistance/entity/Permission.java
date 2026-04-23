package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "permissions", schema = "tutor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissions_seq")
    @SequenceGenerator(name = "permissions_seq", sequenceName = "tutor.permissions_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

}
