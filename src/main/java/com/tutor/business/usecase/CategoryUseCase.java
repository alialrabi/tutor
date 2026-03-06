package com.tutor.business.usecase;

import com.tutor.business.dto.CategoryDto;
import com.tutor.business.dto.CategoryTutorsDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;

public interface CategoryUseCase {

    ResponseDataModel<CategoryDto> findAll(SearchRequest searchRequest);
    ResponseDataModel<CategoryTutorsDto> listTutorsByCategory(SearchRequest searchRequest);
}
