package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SessionDto {
    private Long tutorId;
    private TutorDto tutor;
    private UserDto userProfile;
    private TimeSlotDto timeSlot;
}
