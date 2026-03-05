package com.tutor.business.service;

import com.tutor.business.mapper.TutorMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.persistance.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService  {
    private final TutorRepository tutorRepository;
    private final CommonCriteria commonCriteria;
    private final TutorMapper tutorMapper;

    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(tutorRepository, searchRequest, tutorMapper::toDto);
    }
}
