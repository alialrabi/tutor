package com.tutor.business.usecase;

import com.tutor.business.dto.SearchRequest;
import com.tutor.business.dto.UserDto;
import com.tutor.business.service.UserService;
import com.tutor.common.dto.ResponseDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserService userService;

    public ResponseDataModel<UserDto> findAll(SearchRequest searchRequest) {
        return userService.findAll(searchRequest);
    }

}
