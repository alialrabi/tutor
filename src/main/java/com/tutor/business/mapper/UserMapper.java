package com.tutor.business.mapper;

import com.tutor.business.dto.TutorDto;
import com.tutor.business.dto.UserDto;
import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserProfile tutor);
}
