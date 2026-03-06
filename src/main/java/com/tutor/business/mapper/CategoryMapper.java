package com.tutor.business.mapper;

import com.tutor.business.dto.CategoryDto;
import com.tutor.persistance.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
