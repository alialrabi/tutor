package com.tutor.business.usecase;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.security.AppUserDetails;

public interface TimeSlotUseCase {
    ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest);
    TimeSlotDto findById(Long id);
    TimeSlotDto create(TimeSlotRequest timeSlotRequest, AppUserDetails userDetails);
    TimeSlotDto update(Long id, TimeSlotDto timeSlotDto);
    void delete(Long id);
}
