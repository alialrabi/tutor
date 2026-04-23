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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TimeSlotDto> findByTutorId(Long tutorId) {
        return timeSlotRepository.findByTutorId(tutorId).stream()
                .map(timeSlotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TimeSlotDto> create(List<TimeSlotRequest> timeSlotRequests, AppUserDetails userDetails) {
        log.info("Updating time slots for tutorId {}", userDetails.getTutorId());
        timeSlotRepository.deleteByTutorId(userDetails.getTutorId());
        List<TimeSlot> timeSlots = timeSlotRequests.stream().map(timeSlotRequest -> {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setStartTime(timeSlotRequest.getStartTime());
            timeSlot.setEndTime(timeSlotRequest.getEndTime());
            timeSlot.setDayOfWeek(timeSlotRequest.getDayOfWeek());
            timeSlot.setTutorId(userDetails.getTutorId());
            return timeSlot;
        }).collect(Collectors.toList());

        List<TimeSlot> savedTimeSlots = timeSlotRepository.saveAll(timeSlots);
        return savedTimeSlots.stream().map(timeSlotMapper::toDto).collect(Collectors.toList());
    }

    public TimeSlotDto update(Long id, TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TimeSlot not found"));
        // Update fields as needed
        timeSlot.setStartTime(timeSlotDto.getStartTime());
        timeSlot.setEndTime(timeSlotDto.getEndTime());
        timeSlot.setDayOfWeek(timeSlotDto.getDayOfWeek());
        TimeSlot updatedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDto(updatedTimeSlot);
    }

    public void updateReservation(Long id, Boolean isReserved) {
        log.info("Update TimeSlot reservation to  : {}", isReserved);
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TimeSlot not found"));

        timeSlotRepository.save(timeSlot);
    }

    public void delete(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new BusinessException("TimeSlot not found");
        }
        timeSlotRepository.deleteById(id);
    }
}
