package com.tutor.business.mapper;

import com.tutor.business.dto.TutorDto;
import com.tutor.common.dto.AuthDto;
import com.tutor.persistance.entity.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TutorMapper {
    TutorDto toDto(Tutor tutor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "totalReviews", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "numberOfSessions", ignore = true)
    Tutor toEntity(AuthDto.TutorRegisterRequest request);
}
