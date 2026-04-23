package com.tutor.business.mapper;

import com.tutor.business.dto.CommentDto;
import com.tutor.persistance.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);
}
