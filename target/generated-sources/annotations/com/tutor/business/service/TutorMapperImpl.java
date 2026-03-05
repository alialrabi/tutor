package com.tutor.business.service;

import com.tutor.business.dto.TutorDto;
import com.tutor.persistance.entity.Tutor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-05T15:55:37+0200",
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
        tutorDto.setName( tutor.getName() );
        tutorDto.setSubject( tutor.getSubject() );
        tutorDto.setEmail( tutor.getEmail() );

        return tutorDto;
    }
}
