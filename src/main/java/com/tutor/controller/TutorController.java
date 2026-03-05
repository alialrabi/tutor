package com.tutor.controller;

import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.usecase.TutorUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorUseCase tutorUseCase;

    @PostMapping("/search")
    public GenericResponseEntity<ResponseDataModel<TutorDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<TutorDto> data = tutorUseCase.findAll(searchRequest);
        return GenericResponseEntity.success(data);
    }

}
