package com.tutor.business.service;


import com.tutor.business.dto.SessionDto;
import com.tutor.business.mapper.SessionMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.Session;
import com.tutor.persistance.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService  {

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

    public SessionDto create(SessionDto sessionDto) {
        Session session = sessionMapper.toEntity(sessionDto);
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
}
