package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.service.SessionService;
import com.tutor.business.service.TimeSlotService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionUseCaseImpl implements SessionUseCase {

    private final SessionService sessionService;
    private final TimeSlotService timeSlotService;

    @Override
    public ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest) {
        return sessionService.findAll(searchRequest);
    }

    @Override
    public SessionDto findById(Long id) {
        return sessionService.findById(id);
    }

    @Override
    public Boolean create(SessionRequest sessionRequest, AppUserDetails userDetails) {
        log.info("create session for tutor id {}", sessionRequest.getTutorId());
        SessionDto sessionDto = sessionService.create(sessionRequest, userDetails);
        timeSlotService.updateReservation(sessionRequest.getTimeSlotId(), true);
        return true;
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
