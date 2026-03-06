package com.tutor.business.service;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.mapper.TimeSlotMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.TimeSlot;
import com.tutor.persistance.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public TimeSlotDto create(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDto);
        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDto(savedTimeSlot);
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

    public void delete(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new BusinessException("TimeSlot not found");
        }
        timeSlotRepository.deleteById(id);
    }
}
