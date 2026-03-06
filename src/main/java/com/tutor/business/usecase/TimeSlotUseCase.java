package com.tutor.business.usecase;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;

public interface TimeSlotUseCase {
    ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest);
    TimeSlotDto findById(Long id);
    TimeSlotDto create(TimeSlotDto timeSlotDto);
    TimeSlotDto update(Long id, TimeSlotDto timeSlotDto);
    void delete(Long id);
}
