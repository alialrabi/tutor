package com.tutor.controller;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.usecase.SessionUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.SessionRequest;
import com.tutor.security.AppUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionUseCase sessionUseCase;

    @PostMapping("/search")
    public GenericResponseEntity<ResponseDataModel<SessionDto>> findAll(
            @RequestBody SearchRequest searchRequest) {
        ResponseDataModel<SessionDto> data = sessionUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/{id}")
    public GenericResponseEntity<SessionDto> findById(@PathVariable Long id) {
        SessionDto data = sessionUseCase.findById(id);
        return GenericResponseEntity.generateResponse(data);
    }

    @PostMapping("create")
    public GenericResponseEntity<Boolean> create(
            @RequestBody @Valid SessionRequest sessionRequest,
            @AuthenticationPrincipal AppUserDetails userDetails) {
        Boolean data = sessionUseCase.create(sessionRequest, userDetails);
        return GenericResponseEntity.generateResponse(data);
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        sessionUseCase.delete(id);
        return GenericResponseEntity.generateResponse(null);
    }

    @GetMapping("/list/user")
    public GenericResponseEntity<List<SessionDto>> findByUserProfileId(@RequestParam("id") Long userProfileId) {
        List<SessionDto> data = sessionUseCase.findByUserProfileId(userProfileId);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/list/tutor")
    public GenericResponseEntity<List<SessionDto>> findByTutorId(@RequestParam("id") Long tutorId) {
        List<SessionDto> data = sessionUseCase.findByTutorId(tutorId);
        return GenericResponseEntity.generateResponse(data);
    }
}
