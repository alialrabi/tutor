package com.tutor.controller;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.dto.TutorTimeSlotResponseDto;
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

import java.util.List;

@RestController
@RequestMapping("/api/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotUseCase timeSlotUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<TimeSlotDto>> findAll(@RequestBody SearchRequest searchRequest) {
        try {
            ResponseDataModel<TimeSlotDto> data = timeSlotUseCase.findAll(searchRequest);
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @GetMapping("/tutor/{id}/timeslots")
    public GenericResponseEntity<List<TutorTimeSlotResponseDto>> findByTutorIdWithDates(@PathVariable Long id) {
        try {
            List<TutorTimeSlotResponseDto> data = timeSlotUseCase.findByTutorIdWithDates(id);
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @GetMapping("/tutor/timeslots")
    public GenericResponseEntity<List<TimeSlotDto>> findByTutor(@AuthenticationPrincipal AppUserDetails userDetails) {
        try {
            List<TimeSlotDto> data = timeSlotUseCase.findByTutorId(userDetails.getTutorId());
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public GenericResponseEntity<TimeSlotDto> findById(@PathVariable Long id) {
        try {
            TimeSlotDto data = timeSlotUseCase.findById(id);
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @PostMapping(value="/create")
    public GenericResponseEntity<List<TimeSlotDto>> create(
            @RequestBody @Valid List<TimeSlotRequest> timeSlotRequests,
            @AuthenticationPrincipal AppUserDetails userDetails) {
        try {
            List<TimeSlotDto> data = timeSlotUseCase.create(timeSlotRequests, userDetails);
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<TimeSlotDto> update(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        try {
            TimeSlotDto data = timeSlotUseCase.update(id, timeSlotDto);
            return GenericResponseEntity.generateResponse(data);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            timeSlotUseCase.delete(id);
            return GenericResponseEntity.generateResponse(null);
        } catch (Exception e) {
            return GenericResponseEntity.error(e.getMessage());
        }
    }
}
