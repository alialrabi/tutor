package com.tutor.business.service;

import com.tutor.business.dto.TutorDto;
import com.tutor.persistance.entity.Tutor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-05T17:31:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class TutorMapperImpl implements TutorMapper {

    @Override
    public TutorDto toDto(Tutor tutor) {
        if ( tutor == null ) {
            return null;
        }

        TutorDto tutorDto = new TutorDto();

        tutorDto.setId( tutor.getId() );
        tutorDto.setUser( tutor.getUser() );
        tutorDto.setBio( tutor.getBio() );
        tutorDto.setExperienceYears( tutor.getExperienceYears() );
        tutorDto.setHourlyRate( tutor.getHourlyRate() );
        tutorDto.setAcceptsOneToOne( tutor.getAcceptsOneToOne() );
        tutorDto.setAcceptsOneToMany( tutor.getAcceptsOneToMany() );
        tutorDto.setRating( tutor.getRating() );
        tutorDto.setTotalReviews( tutor.getTotalReviews() );
        tutorDto.setStatus( tutor.getStatus() );
        tutorDto.setNumberOfSessions( tutor.getNumberOfSessions() );
        tutorDto.setVideoId( tutor.getVideoId() );

        return tutorDto;
    }
}
