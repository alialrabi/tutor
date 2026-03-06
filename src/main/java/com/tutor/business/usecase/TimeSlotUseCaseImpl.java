package com.tutor.business.usecase;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.service.TimeSlotService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Transactional
    @Override
    public TimeSlotDto create(TimeSlotRequest timeSlotRequest, AppUserDetails userDetails) {
        log.info("create time slot for tutor id: {}", userDetails.getTutorId());
        return timeSlotService.create(timeSlotRequest, userDetails);
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
