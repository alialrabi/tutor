package com.tutor.business.usecase;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.service.TimeSlotService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotUseCaseImpl implements TimeSlotUseCase {

    private final TimeSlotService timeSlotService;

    @Override
    public ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest) {
        return timeSlotService.findAll(searchRequest);
    }

    @Override
    public TimeSlotDto findById(Long id) {
        return timeSlotService.findById(id);
    }

    @Override
    public TimeSlotDto create(TimeSlotDto timeSlotDto) {
        return timeSlotService.create(timeSlotDto);
    }

    @Override
    public TimeSlotDto update(Long id, TimeSlotDto timeSlotDto) {
        return timeSlotService.update(id, timeSlotDto);
    }

    @Override
    public void delete(Long id) {
        timeSlotService.delete(id);
    }
}
