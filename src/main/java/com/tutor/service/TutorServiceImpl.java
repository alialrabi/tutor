package com.tutor.service;

import com.tutor.CommonCriteria;
import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.service.TutorMapper;
import com.tutor.dto.ResponseDataModel;
import com.tutor.persistance.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final CommonCriteria commonCriteria;
    private final TutorMapper tutorMapper;

    @Override
    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(tutorRepository, searchRequest, tutorMapper::toDto);
    }
}
