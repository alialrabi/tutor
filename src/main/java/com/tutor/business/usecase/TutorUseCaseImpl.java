package com.tutor.business.usecase;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.service.TutorService;
import com.tutor.dto.ResponseDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorUseCaseImpl implements TutorUseCase {

    private  final TutorService tutorService;

    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return tutorService.findAll(searchRequest);
    }

}
