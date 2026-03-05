package com.tutor.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tutors", schema = "tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutor_seq_gen")
    @SequenceGenerator(
            name = "tutor_seq_gen",
            sequenceName = "tutor_seq",
            schema = "tutor",
            allocationSize = 1
    )
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "accepts_one_to_one")
    private Boolean acceptsOneToOne = true;

    @Column(name = "accepts_one_to_many")
    private Boolean acceptsOneToMany = false;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @Column(nullable = false)
    private Long status = 0L;

    @Column(name = "no_of_sessions")
    private Integer numberOfSessions;

    @Column(name = "video_id")
    private String videoId;

    @Lob
    @Column(name = "image")
    private byte[] image;

}