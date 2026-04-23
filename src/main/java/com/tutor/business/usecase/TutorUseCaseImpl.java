package com.tutor.business.usecase;

import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.service.TutorService;
import com.tutor.common.dto.ResponseDataModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorUseCaseImpl implements TutorUseCase {

    private  final TutorService tutorService;

    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return tutorService.findAll(searchRequest);
    }

    @Override
    public TutorDto findOne(Long id) {
        return tutorService.findById(id);
    }

    @Override
    public TutorDto save(AuthDto.TutorRegisterRequest request) {
        return tutorService.save(request);
    }
}
