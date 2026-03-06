package com.tutor.business.mapper;

import com.tutor.business.dto.CategoryTutorsDto;
import com.tutor.persistance.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryTutorsMapper {
    CategoryTutorsDto toDto(Category category);
    Category toEntity(CategoryTutorsDto categoryTutorsDto);
}
