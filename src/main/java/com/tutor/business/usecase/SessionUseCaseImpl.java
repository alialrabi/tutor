package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.service.SessionService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionUseCaseImpl implements SessionUseCase {

    private final SessionService sessionService;

    @Override
    public ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest) {
        return sessionService.findAll(searchRequest);
    }

    @Override
    public SessionDto findById(Long id) {
        return sessionService.findById(id);
    }

    @Override
    public SessionDto create(SessionDto sessionDto) {
        return sessionService.create(sessionDto);
    }

    @Override
    public SessionDto update(Long id, SessionDto sessionDto) {
        return sessionService.update(id, sessionDto);
    }

    @Override
    public void delete(Long id) {
        sessionService.delete(id);
    }
}
