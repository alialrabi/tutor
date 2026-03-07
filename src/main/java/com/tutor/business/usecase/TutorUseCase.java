package com.tutor.business.usecase;

import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.common.dto.ResponseDataModel;

public interface TutorUseCase {

    ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest);
    TutorDto findOne(Long id);
    TutorDto save(AuthDto.TutorRegisterRequest request);
}
