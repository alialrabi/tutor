package com.tutor.business.service;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.mapper.SessionMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.enums.SessionStatusEnum;
import com.tutor.exception.BusinessException;
import com.tutor.exception.EntityNotFoundException;
import com.tutor.persistance.entity.Session;
import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import com.tutor.persistance.repository.SessionRepository;
import com.tutor.persistance.repository.TutorRepository;
import com.tutor.persistance.repository.UserProfileRepository;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CommonCriteria commonCriteria;
    private final SessionMapper sessionMapper;
    private final TutorRepository tutorRepository;
    private final UserProfileRepository userProfileRepository;

    public ResponseDataModel<SessionDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(sessionRepository, searchRequest, sessionMapper::toDto);
    }

    public SessionDto findById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Session not found"));
        return sessionMapper.toDto(session);
    }

    public Session findEntityById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Session not found"));
    }


    public SessionDto create(SessionRequest sessionRequest, AppUserDetails userDetails) {
        log.info("Create session for tutor id {} and user id {}",
                sessionRequest.getTutorId(), userDetails.getUserId());
        Session session = new Session();

       /* Tutor tutor = tutorRepository.findById(sessionRequest.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor", "id", sessionRequest.getTutorId()));

        UserProfile userProfile = userProfileRepository.findById(sessionRequest.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor", "id", sessionRequest.getTutorId()));
*/

        session.setTutorId(sessionRequest.getTutorId());

        session.setDate(sessionRequest.getDate());
        session.setStartTime(sessionRequest.getStartTime());
        session.setEndTime(sessionRequest.getEndTime());
        session.setUserProfileId(userDetails.getUserId());
        session.setStatus(SessionStatusEnum.RESERVED.getValue());
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

    public void updateRoomDetails(Long sessionId, String roomId, String roomUrl) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException("Session not found"));
        session.setRoomId(roomId);
        sessionRepository.save(session);
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

    public List<SessionDto> findByTutorIdAndDateBetween(Long tutorId, LocalDate startDate, LocalDate endDate) {
        return sessionRepository.findByTutorIdAndDateBetween(tutorId, startDate, endDate).stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
