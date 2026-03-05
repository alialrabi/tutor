package com.tutor.business.usecase;

import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.UserDto;
import com.tutor.common.dto.ResponseDataModel;

public interface UserUseCase {
    ResponseDataModel<UserDto> findAll(SearchRequest searchRequest);
}
