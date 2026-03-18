package com.tutor.business.usecase;

import com.tutor.business.dto.CommentDto;
import com.tutor.business.service.CommentService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentUseCaseImpl implements CommentUseCase {

    private final CommentService commentService;

    @Override
    public ResponseDataModel<CommentDto> findAll(SearchRequest searchRequest) {
        return commentService.findAll(searchRequest);
    }

    @Override
    public CommentDto findById(Long id) {
        return commentService.findById(id);
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        return commentService.create(commentDto);
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        return commentService.update(id, commentDto);
    }

    @Override
    public void delete(Long id) {
        commentService.delete(id);
    }

    @Override
    public List<CommentDto> findByTutorId(Long tutorId) {
        return commentService.findByTutorId(tutorId);
    }
}
