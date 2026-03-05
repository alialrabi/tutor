package com.tutor.service;

import com.tutor.dto.SearchRequest;
import com.tutor.dto.TutorDto;
import org.springframework.data.domain.Page;

public interface TutorService {
    Page<TutorDto> findAll(SearchRequest searchRequest);
}
