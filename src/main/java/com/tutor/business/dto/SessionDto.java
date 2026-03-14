package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class SessionDto {
    private Long id;
    private Long tutorId;
    private TutorDto tutor;
    private UserDto userProfile;
    private String roomId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
