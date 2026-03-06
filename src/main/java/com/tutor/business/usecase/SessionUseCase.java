package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;

public interface SessionUseCase {
    ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest);
    SessionDto findById(Long id);
    SessionDto create(SessionDto sessionDto);
    SessionDto update(Long id, SessionDto sessionDto);
    void delete(Long id);
}
