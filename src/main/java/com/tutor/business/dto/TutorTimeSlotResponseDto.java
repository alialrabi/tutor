package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class TutorTimeSlotResponseDto extends BaseDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer dayOfWeek;
    private Boolean isReserved;
    private Long tutorId;
}
