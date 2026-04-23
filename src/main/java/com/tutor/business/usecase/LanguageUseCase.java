package com.tutor.business.usecase;

import com.tutor.business.dto.LanguageDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;

public interface LanguageUseCase {
    ResponseDataModel<LanguageDto> findAll(SearchRequest searchRequest);
    LanguageDto findById(Long id);
    LanguageDto create(LanguageDto languageDto);
    LanguageDto update(Long id, LanguageDto languageDto);
    void delete(Long id);
}
