package com.tutor.business.usecase;

import com.tutor.business.dto.LanguageDto;
import com.tutor.business.service.LanguageService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageUseCaseImpl implements LanguageUseCase {

    private final LanguageService languageService;

    @Override
    public ResponseDataModel<LanguageDto> findAll(SearchRequest searchRequest) {
        return languageService.findAll(searchRequest);
    }

    @Override
    public LanguageDto findById(Long id) {
        return languageService.findById(id);
    }

    @Override
    public LanguageDto create(LanguageDto languageDto) {
        return languageService.create(languageDto);
    }

    @Override
    public LanguageDto update(Long id, LanguageDto languageDto) {
        return languageService.update(id, languageDto);
    }

    @Override
    public void delete(Long id) {
        languageService.delete(id);
    }
}
