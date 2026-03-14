package com.tutor.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SessionRequest {
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Long tutorId;
}
