package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TimeSlotDto extends BaseDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isReserved;
    private Long tutorId;
}
