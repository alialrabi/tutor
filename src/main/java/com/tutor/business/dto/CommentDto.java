package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentDto extends BaseDto {
    private String content;
    private Long tutorId;
    private UserDto userProfile;
}
