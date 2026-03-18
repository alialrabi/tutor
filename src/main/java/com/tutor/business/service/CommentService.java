package com.tutor.business.service;

import com.tutor.business.dto.CommentDto;
import com.tutor.business.mapper.CommentMapper;
import com.tutor.common.CommonCriteria;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.exception.BusinessException;
import com.tutor.persistance.entity.Comment;
import com.tutor.persistance.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommonCriteria commonCriteria;
    private final CommentMapper commentMapper;

    public ResponseDataModel<CommentDto> findAll(SearchRequest searchRequest) {
        return commonCriteria.findAll(commentRepository, searchRequest, commentMapper::toDto);
    }

    public List<CommentDto> findByTutorId(Long tutorId) {
        return commentRepository.findByTutorId(tutorId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    public CommentDto create(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    public CommentDto update(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comment not found"));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }

    public void delete(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new BusinessException("Comment not found");
        }
        commentRepository.deleteById(id);
    }
}
