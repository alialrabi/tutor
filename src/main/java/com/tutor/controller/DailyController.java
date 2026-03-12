package com.tutor.controller;

import com.tutor.business.usecase.SessionUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.controller.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily")
@RequiredArgsConstructor
public class DailyController {

    private final SessionUseCase sessionUseCase;

    @PostMapping("/room")
    public GenericResponseEntity<String> room(
            @RequestBody RoomRequest roomRequest) {
        return GenericResponseEntity.generateResponse(sessionUseCase.goRoom(roomRequest));
    }
}
