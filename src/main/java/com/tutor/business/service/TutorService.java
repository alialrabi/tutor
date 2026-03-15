package com.tutor.business.service;

import com.tutor.business.mapper.TutorMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.AuthDto;
import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.TutorDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import com.tutor.persistance.repository.TutorRepository;
import com.tutor.persistance.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TutorService  {
    private final TutorRepository tutorRepository;
    private final CommonCriteria commonCriteria;
    private final TutorMapper tutorMapper;

    private final UserProfileRepository userProfileRepository;

    public ResponseDataModel<TutorDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(tutorRepository, searchRequest, tutorMapper::toDto);
    }

    public TutorDto findById(Long id) {
        return tutorMapper.toDto(tutorRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Tutor not found")));
    }

    public TutorDto save(AuthDto.TutorRegisterRequest request) {
        UserProfile userProfile = userProfileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("user with email is not found"));

        if (tutorRepository.findByUser(userProfile).isPresent()) {
            throw new BusinessException("Tutor profile already exists for this user");
        }

        Tutor tutor = tutorMapper.toEntity(request);
        tutor.setUser(userProfile);
        
        tutor.setNumberOfSessions(0);
        tutor.setRating(BigDecimal.ZERO);
        tutor.setTotalReviews(0);
        tutor.setStatus(0L); // 0L for PENDING or similar status

        return tutorMapper.toDto(tutorRepository.save(tutor));
    }
}
