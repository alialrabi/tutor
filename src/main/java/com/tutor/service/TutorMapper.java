package com.tutor.service;

import com.tutor.dto.TutorDto;
import com.tutor.entity.Tutor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorMapper {
    TutorDto toDto(Tutor tutor);
}
