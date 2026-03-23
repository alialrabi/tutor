package com.tutor.controller;

import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.usecase.TutorUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorUseCase tutorUseCase;

    @PostMapping("/search")
    public GenericResponseEntity<ResponseDataModel<TutorDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<TutorDto> data = tutorUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("details")
    public GenericResponseEntity<TutorDto> findOne(@RequestParam Long id) {
        return GenericResponseEntity.generateResponse(tutorUseCase.findOne(id));
    }

    @PostMapping
    public GenericResponseEntity<TutorDto> save(@RequestPart @Valid AuthDto.TutorRegisterRequest request,
                                                @RequestPart("file") MultipartFile file) throws IOException {
        return GenericResponseEntity.generateResponse(tutorUseCase.save(request, file));
    }
}
