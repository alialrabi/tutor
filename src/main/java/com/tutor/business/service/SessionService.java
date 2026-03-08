package com.tutor.business.service;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.mapper.SessionMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.Session;
import com.tutor.persistance.repository.SessionRepository;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CommonCriteria commonCriteria;
    private final SessionMapper sessionMapper;

    public ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(sessionRepository, searchRequest, sessionMapper::toDto);
    }

    public SessionDto findById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Session not found"));
        return sessionMapper.toDto(session);
    }

    public SessionDto create(SessionRequest sessionRequest, Long tutorId, AppUserDetails userDetails) {
        log.info("Create session tutor id {} and userId {}",
                tutorId, userDetails.getUserId());
        Session session = new Session();
        session.setUserProfileId(userDetails.getUserId());
        session.setTutorId(tutorId);
        session.setTimeSlotId(sessionRequest.getTimeSlotId());
        Session savedSession = sessionRepository.save(session);
        return sessionMapper.toDto(savedSession);
    }

    public SessionDto update(Long id, SessionDto sessionDto) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Session not found"));
        // Update fields as needed
        Session updatedSession = sessionRepository.save(session);
        return sessionMapper.toDto(updatedSession);
    }

    public void delete(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new BusinessException("Session not found");
        }
        sessionRepository.deleteById(id);
    }

    public List<SessionDto> findByUserProfileId(Long userProfileId) {
        return sessionRepository.findByUserProfileId(userProfileId).stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<SessionDto> findByTutorId(Long tutorId) {
        return sessionRepository.findByTutorId(tutorId).stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
