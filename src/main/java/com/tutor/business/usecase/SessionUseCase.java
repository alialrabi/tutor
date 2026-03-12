package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.RoomRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.security.AppUserDetails;

import java.util.List;
import java.util.Map;

public interface SessionUseCase {
    ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest);
    SessionDto findById(Long id);
    Boolean create(SessionRequest sessionRequest, AppUserDetails userDetails);
    SessionDto update(Long id, String roomId);
    void delete(Long id);
    List<SessionDto> findByUserProfileId(Long userProfileId);
    List<SessionDto> findByTutorId(Long tutorId);
    String goRoom(RoomRequest roomRequest);

}
