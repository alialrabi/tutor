package com.tutor.business.usecase;

import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.controller.request.RateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TutorUseCase {

    ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest);
    TutorDto findOne(Long id);
    TutorDto save(AuthDto.TutorRegisterRequest request, MultipartFile file) throws IOException;
    void rateTutor(Long id, RateRequest request);
    TutorDto update(Long id, AuthDto.TutorUpdateRequest request) throws IOException;
}
