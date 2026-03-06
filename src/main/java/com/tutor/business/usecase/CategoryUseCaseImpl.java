package com.tutor.business.usecase;

import com.tutor.business.dto.CategoryDto;
import com.tutor.business.dto.CategoryTutorsDto;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.service.CategoryService;
import com.tutor.business.service.TutorService;
import com.tutor.common.dto.FilterColumn;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CategoryService categoryService;

    public ResponseDataModel<CategoryDto> findAll(SearchRequest searchRequest) {
        return categoryService.findAll(searchRequest);
    }

    @Override
    public ResponseDataModel<CategoryTutorsDto> listTutorsByCategory(SearchRequest searchRequest) {
        return categoryService.listTutorsByCategory(searchRequest);
    }

}
