package com.tutor.business.usecase;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.dto.ResponseDataModel;

public interface TutorUseCase {

    ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest);
}
