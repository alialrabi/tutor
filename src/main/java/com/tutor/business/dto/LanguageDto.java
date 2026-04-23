package com.tutor.business.dto;

import com.tutor.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageDto extends BaseDto {
    private String nameAr;
    private String nameEn;
}
