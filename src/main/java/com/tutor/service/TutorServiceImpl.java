package com.tutor.service;

import com.tutor.CommonCriteria;
import com.tutor.dto.SearchRequest;
import com.tutor.dto.TutorDto;
import com.tutor.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final CommonCriteria commonCriteria;
    private final TutorMapper tutorMapper;

    @Override
    public Page<TutorDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(tutorRepository, searchRequest, tutorMapper::toDto);
    }
}
