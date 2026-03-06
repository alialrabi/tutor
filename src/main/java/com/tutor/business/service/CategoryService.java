package com.tutor.business.service;

import com.tutor.business.dto.CategoryDto;
import com.tutor.business.dto.CategoryTutorsDto;
import com.tutor.business.mapper.CategoryMapper;
import com.tutor.business.mapper.CategoryTutorsMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.persistance.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CommonCriteria commonCriteria;
    private final CategoryMapper categoryMapper;
    private final CategoryTutorsMapper categoryTutorsMapper;

    public ResponseDataModel<CategoryDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(categoryRepository, searchRequest, categoryMapper::toDto);
    }

    public ResponseDataModel<CategoryTutorsDto> listTutorsByCategory(SearchRequest searchRequest) {
        return commonCriteria.findAll(categoryRepository, searchRequest, categoryTutorsMapper::toDto);
    }

}
