package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SessionDto extends BaseDto {
    private Long tutorId;
    private List<TimeSlotDto> timeSlots;
}
