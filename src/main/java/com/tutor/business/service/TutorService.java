package com.tutor.business.service;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import org.springframework.data.domain.Page;


public interface TutorService {
    Page<TutorDto> findAll(SearchRequest searchRequest);
}
