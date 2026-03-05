package com.tutor.service;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.dto.ResponseDataModel;

public interface TutorService {
    ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest);
}
