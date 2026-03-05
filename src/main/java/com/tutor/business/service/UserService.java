package com.tutor.business.service;

import com.tutor.common.dto.SearchRequest;
import com.tutor.business.dto.UserDto;
import com.tutor.business.mapper.UserMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.persistance.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProfileRepository userProfileRepository;
    private final CommonCriteria commonCriteria;
    private final UserMapper userMapper;

    public ResponseDataModel<UserDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(userProfileRepository, searchRequest, userMapper::toDto);
    }
}
