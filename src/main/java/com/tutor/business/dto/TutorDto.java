package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TutorDto extends BaseDto {
    private Long id;
    private UserDto userProfile;
    private String title;
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
    private Long categoryId;
}

