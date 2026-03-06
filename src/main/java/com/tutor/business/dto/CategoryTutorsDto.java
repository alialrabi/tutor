package com.tutor.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTutorsDto {
    private Long id;
    private String name;
    private List<TutorDto> tutors;
}
