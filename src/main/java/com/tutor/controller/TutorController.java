package com.tutor.controller;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.dto.GenericResponseEntity;
import com.tutor.dto.ResponseDataModel;
import com.tutor.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/search")
    public GenericResponseEntity<ResponseDataModel<TutorDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<TutorDto> data = tutorService.findAll(searchRequest);
        return GenericResponseEntity.success(data);
    }
}
