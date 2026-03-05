package com.tutor.business.mapper;

import com.tutor.business.dto.TutorDto;
import com.tutor.persistance.entity.Tutor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorMapper {
    TutorDto toDto(Tutor tutor);
}
