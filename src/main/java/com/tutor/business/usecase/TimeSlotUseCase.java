package com.tutor.business.usecase;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.dto.TutorTimeSlotResponseDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.security.AppUserDetails;

import java.util.List;

public interface TimeSlotUseCase {
    ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest);
    TimeSlotDto findById(Long id);
    List<TimeSlotDto> create(List<TimeSlotRequest> timeSlotRequests, AppUserDetails userDetails);
    TimeSlotDto update(Long id, TimeSlotDto timeSlotDto);
    void delete(Long id);
    List<TimeSlotDto> findByTutorId(Long tutorId);
    List<TutorTimeSlotResponseDto> findByTutorIdWithDates(Long tutorId);
}
