package com.tutor.business.mapper;

import com.tutor.business.dto.SessionDto;
import com.tutor.persistance.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TimeSlotMapper.class})
public interface SessionMapper {
    @Mapping(source = "tutor.id", target = "tutorId")
    SessionDto toDto(Session session);

    @Mapping(source = "tutorId", target = "tutor.id")
    Session toEntity(SessionDto sessionDto);
}
