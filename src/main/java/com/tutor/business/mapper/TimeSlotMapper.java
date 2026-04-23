package com.tutor.business.mapper;

import com.tutor.business.dto.TimeSlotDto;
import com.tutor.persistance.entity.TimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeSlotMapper {
    TimeSlotDto toDto(TimeSlot timeSlot);
    TimeSlot toEntity(TimeSlotDto timeSlotDto);
}
