package com.tutor.business.dto;

import com.tutor.persistance.entity.UserProfile;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class TutorDto extends BaseDto {
    private Long id;
    private UserProfile user;
    private String bio;
    private Integer experienceYears;
    private BigDecimal hourlyRate;
    private Boolean acceptsOneToOne = true;
    private Boolean acceptsOneToMany = false;
    private BigDecimal rating = BigDecimal.ZERO;
    private Integer totalReviews = 0;
    private Long status = 0L;
    private Integer numberOfSessions;
    private String videoId;
}

