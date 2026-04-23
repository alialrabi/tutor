package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class TimeSlotDto {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer dayOfWeek;
    private Long tutorId;
}
