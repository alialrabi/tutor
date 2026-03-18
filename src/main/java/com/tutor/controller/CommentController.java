package com.tutor.controller;

import com.tutor.business.dto.CommentDto;
import com.tutor.business.usecase.CommentUseCase;
import com.tutor.common.dto.GenericResponseEntity;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentUseCase commentUseCase;

    @PostMapping("/list")
    public GenericResponseEntity<ResponseDataModel<CommentDto>> findAll(@RequestBody SearchRequest searchRequest) {
        ResponseDataModel<CommentDto> data = commentUseCase.findAll(searchRequest);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/tutor/{tutorId}")
    public GenericResponseEntity<List<CommentDto>> findByTutorId(@PathVariable Long tutorId) {
        List<CommentDto> data = commentUseCase.findByTutorId(tutorId);
        return GenericResponseEntity.generateResponse(data);
    }

    @GetMapping("/{id}")
    public GenericResponseEntity<CommentDto> findById(@PathVariable Long id) {
        CommentDto data = commentUseCase.findById(id);
        return GenericResponseEntity.generateResponse(data);
    }

    @PostMapping
    public GenericResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
        CommentDto data = commentUseCase.create(commentDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        CommentDto data = commentUseCase.update(id, commentDto);
        return GenericResponseEntity.generateResponse(data);
    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<Void> delete(@PathVariable Long id) {
        commentUseCase.delete(id);
        return GenericResponseEntity.generateResponse(null);
    }
}
