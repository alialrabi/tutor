package com.tutor.business.service;

import com.tutor.business.dto.LanguageDto;
import com.tutor.business.mapper.LanguageMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.LuLanguage;
import com.tutor.persistance.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final CommonCriteria commonCriteria;
    private final LanguageMapper languageMapper;

    public ResponseDataModel<LanguageDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(languageRepository, searchRequest, languageMapper::toDto);
    }

    public LanguageDto findById(Long id) {
        LuLanguage luLanguage = languageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Language not found"));
        return languageMapper.toDto(luLanguage);
    }

    public LanguageDto create(LanguageDto languageDto) {
        LuLanguage luLanguage = languageMapper.toEntity(languageDto);
        LuLanguage savedLanguage = languageRepository.save(luLanguage);
        return languageMapper.toDto(savedLanguage);
    }

    public LanguageDto update(Long id, LanguageDto languageDto) {
        LuLanguage luLanguage = languageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Language not found"));
        luLanguage.setNameAr(languageDto.getNameAr());
        luLanguage.setNameEn(languageDto.getNameEn());
        LuLanguage updatedLanguage = languageRepository.save(luLanguage);
        return languageMapper.toDto(updatedLanguage);
    }

    public void delete(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new BusinessException("Language not found");
        }
        languageRepository.deleteById(id);
    }
}
