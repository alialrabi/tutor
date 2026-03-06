package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
public class TimeSlotDto extends BaseDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isReserved;
}
