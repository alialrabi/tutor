package com.tutor.business.service;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.mapper.TimeSlotMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.TimeSlot;
import com.tutor.persistance.repository.TimeSlotRepository;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final CommonCriteria commonCriteria;
    private final TimeSlotMapper timeSlotMapper;

    public ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(timeSlotRepository, searchRequest, timeSlotMapper::toDto);
    }

    public TimeSlotDto findById(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TimeSlot not found"));
        return timeSlotMapper.toDto(timeSlot);
    }

    public TimeSlotDto create(TimeSlotRequest timeSlotRequest, AppUserDetails userDetails) {
        log.info("Create TimeSlot start date : {} and end date : {}", timeSlotRequest.getStartTime(),
                timeSlotRequest.getEndTime());
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(timeSlotRequest.getStartTime());
        timeSlot.setEndTime(timeSlotRequest.getEndTime());
        timeSlot.setTutorId(userDetails.getTutorId());
        return timeSlotMapper.toDto(timeSlotRepository.save(timeSlot));
    }

    public TimeSlotDto update(Long id, TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TimeSlot not found"));
        // Update fields as needed
        timeSlot.setStartTime(timeSlotDto.getStartTime());
        timeSlot.setEndTime(timeSlotDto.getEndTime());
        timeSlot.setIsReserved(timeSlotDto.getIsReserved());
        TimeSlot updatedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDto(updatedTimeSlot);
    }

    public void updateReservation(Long id, Boolean isReserved) {
        log.info("Update TimeSlot reservation to  : {}", isReserved);
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TimeSlot not found"));

        if (timeSlot.getIsReserved() && isReserved) {
            log.warn("Time slot is already reserved.");
            throw new BusinessException("Time slot is already reserved.");
        }

        timeSlot.setIsReserved(isReserved);
        timeSlotRepository.save(timeSlot);
    }

    public void delete(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new BusinessException("TimeSlot not found");
        }
        timeSlotRepository.deleteById(id);
    }
}
