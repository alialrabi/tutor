package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.service.SessionService;
import com.tutor.business.service.TimeSlotService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.RoomRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.dto.DailyRoomResponse;
import com.tutor.exception.BusinessException;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionUseCaseImpl implements SessionUseCase {

    private final SessionService sessionService;
    private final TimeSlotService timeSlotService;
    private final RestTemplate dailyRestTemplate;

    @Value("${daily.api.base-url}")
    private String dailyApiBaseUrl;

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
        TimeSlotDto timeSlotDto = timeSlotService.findById(sessionRequest.getTimeSlotId());
        log.info("create session for tutor id {}", timeSlotDto.getTutorId());
        SessionDto sessionDto = sessionService.create(sessionRequest, timeSlotDto.getTutorId(), userDetails);
        timeSlotService.updateReservation(sessionRequest.getTimeSlotId(), true);
        return true;
    }


    @Override
    public SessionDto update(Long id, String roomId) {
        log.info("update session for tutor id {}", id);
        return sessionService.updateRoomDetails(id, roomId);
    }

    @Override
    public void delete(Long id) {
        sessionService.delete(id);
    }

    @Override
    public List<SessionDto> findByUserProfileId(Long userProfileId) {
        return sessionService.findByUserProfileId(userProfileId);
    }

    @Override
    public List<SessionDto> findByTutorId(Long tutorId) {
        return sessionService.findByTutorId(tutorId);
    }

    @Transactional
    @Override
    public String goRoom(RoomRequest roomRequest) {
        log.info("start session room {}", roomRequest);
        SessionDto session = findById(roomRequest.getSessionId());

        if (session.getRoomId() != null && !session.getRoomId().isEmpty()) {
            log.info("Room already created for session id {}", roomRequest.getSessionId());
           return session.getRoomId();
        }

        DailyRoomResponse response = createNewRoom();

        update(roomRequest.getSessionId(), response.getUrl());
        return response.getUrl();
    }

    private DailyRoomResponse createNewRoom() {
        log.info("Create new room for the session");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> properties=new HashMap<>();
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(properties, headers);

        DailyRoomResponse response = dailyRestTemplate.postForObject(dailyApiBaseUrl + "/rooms", request, DailyRoomResponse.class);

        if (response == null || response.getUrl() == null) {
            throw new BusinessException("Failed to create Daily room");
        }
        return response;
    }

}
