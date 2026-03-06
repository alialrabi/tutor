package com.tutor.controller;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.usecase.TimeSlotUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.security.AppUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotUseCase timeSlotUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<TimeSlotDto>> findAll(@RequestBody SearchRequest searchRequest) {
            ResponseDataModel<TimeSlotDto> data = timeSlotUseCase.findAll(searchRequest);
            return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/{id}")
    public GenericResponseEntity<TimeSlotDto> findById(@PathVariable Long id) {
        TimeSlotDto data = timeSlotUseCase.findById(id);
        return GenericResponseEntity.generateResponse(data);
    }

    @PostMapping("create")
    public GenericResponseEntity<TimeSlotDto> create(
            @RequestBody @Valid TimeSlotRequest timeSlotRequest,
            @AuthenticationPrincipal AppUserDetails userDetails) {
        TimeSlotDto data = timeSlotUseCase.create(timeSlotRequest, userDetails);
        return GenericResponseEntity.generateResponse(data);
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<TimeSlotDto> update(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        TimeSlotDto data = timeSlotUseCase.update(id, timeSlotDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        timeSlotUseCase.delete(id);
        return GenericResponseEntity.generateResponse(null);
    }
}
