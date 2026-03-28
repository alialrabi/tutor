package com.tutor.business.usecase;

import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.business.service.TutorService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.controller.request.RateRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TutorUseCaseImpl implements TutorUseCase {

    private  final TutorService tutorService;

    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return tutorService.findAll(searchRequest);
    }

    @Override
    public TutorDto update(Long id, AuthDto.TutorUpdateRequest request) throws IOException {
        return tutorService.update(id, request);
    }

    @Override
    public void rateTutor(Long id, RateRequest request) {
        tutorService.rateTutor(id, request);
    }

    @Override
    public TutorDto findOne(Long id) {
        return tutorService.findById(id);
    }

    @Override
    public TutorDto save(AuthDto.TutorRegisterRequest request, MultipartFile file) throws IOException {
        return tutorService.save(request, file);
    }
}
