package com.tutor.controller.request;

import com.tutor.business.dto.TimeSlotDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionRequest  {
    @NotNull
    private Long tutorId;
    @NotNull
    private Long timeSlotId;
}
