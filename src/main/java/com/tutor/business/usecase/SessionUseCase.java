package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.security.AppUserDetails;

public interface SessionUseCase {
    ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest);
    SessionDto findById(Long id);
    Boolean create(SessionRequest sessionRequest, AppUserDetails userDetails);
    SessionDto update(Long id, SessionDto sessionDto);
    void delete(Long id);
}
