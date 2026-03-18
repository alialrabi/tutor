package com.tutor.business.mapper;

import com.tutor.business.dto.LanguageDto;
import com.tutor.persistance.entity.LuLanguage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageDto toDto(LuLanguage luLanguage);
    LuLanguage toEntity(LanguageDto languageDto);
}
