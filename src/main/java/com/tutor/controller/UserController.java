package com.tutor.controller;

import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.UserDto;
import com.tutor.business.usecase.UserUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("")
    public GenericResponseEntity<ResponseDataModel<UserDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<UserDto> data = userUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

}
