package com.tutor.controller;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.usecase.SessionUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionUseCase sessionUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<SessionDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<SessionDto> data = sessionUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
}

    @GetMapping("/{id}")
    public GenericResponseEntity<SessionDto> findById(@PathVariable Long id) {
        SessionDto data = sessionUseCase.findById(id);
        return GenericResponseEntity.generateResponse(data);

    }

    @PostMapping
    public GenericResponseEntity<SessionDto> create(@RequestBody SessionDto sessionDto) {
        SessionDto data = sessionUseCase.create(sessionDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<SessionDto> update(@PathVariable Long id, @RequestBody SessionDto sessionDto) {
        SessionDto data = sessionUseCase.update(id, sessionDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        sessionUseCase.delete(id);
        return GenericResponseEntity.generateResponse(null);
    }

}
