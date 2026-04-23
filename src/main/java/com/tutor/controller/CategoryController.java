package com.tutor.controller;

import com.tutor.business.dto.CategoryDto;
import com.tutor.business.dto.CategoryTutorsDto;
import com.tutor.business.usecase.CategoryUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<CategoryDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<CategoryDto> data = categoryUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

    @PostMapping("/tutors")
    public GenericResponseEntity<ResponseDataModel<CategoryTutorsDto>> listTutor(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<CategoryTutorsDto> data = categoryUseCase.listTutorsByCategory(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }


}
