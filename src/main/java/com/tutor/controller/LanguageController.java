package com.tutor.controller;

import com.tutor.business.dto.LanguageDto;
import com.tutor.business.usecase.LanguageUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageUseCase languageUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<LanguageDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<LanguageDto> data = languageUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/{id}")
    public GenericResponseEntity<LanguageDto> findById(@PathVariable Long id) {
        LanguageDto data = languageUseCase.findById(id);
        return GenericResponseEntity.generateResponse(data);
    }

    @PostMapping
    public GenericResponseEntity<LanguageDto> create(@RequestBody LanguageDto languageDto) {
        LanguageDto data = languageUseCase.create(languageDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<LanguageDto> update(@PathVariable Long id, @RequestBody LanguageDto languageDto) {
        LanguageDto data = languageUseCase.update(id, languageDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        languageUseCase.delete(id);
        return GenericResponseEntity.generateResponse(null);
    }
}
