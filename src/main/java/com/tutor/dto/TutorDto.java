package com.tutor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TutorDto extends BaseDto {
    private String name;
    private String subject;
    private String email;
}
