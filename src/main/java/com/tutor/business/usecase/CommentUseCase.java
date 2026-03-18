package com.tutor.business.usecase;

import com.tutor.business.dto.CommentDto;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;

import java.util.List;

public interface CommentUseCase {
    ResponseDataModel<CommentDto> findAll(SearchRequest searchRequest);
    CommentDto findById(Long id);
    CommentDto create(CommentDto commentDto);
    CommentDto update(Long id, CommentDto commentDto);
    void delete(Long id);
    List<CommentDto> findByTutorId(Long tutorId);
}
