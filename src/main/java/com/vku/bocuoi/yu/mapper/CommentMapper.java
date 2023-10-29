package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.CommentDto;
import com.vku.bocuoi.yu.model.entity.Comment;
import com.vku.bocuoi.yu.repository.ActivityRepository;
import com.vku.bocuoi.yu.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;

public class CommentMapper {
    ActivityRepository activityRepository;
    CommentRepository commentRepository;
    private static CommentMapper INSTANCE;
    public static CommentMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CommentMapper();
        }
        return INSTANCE;
    }
    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setActivityId(comment.getActivity().getId());
        commentDto.setTitle(comment.getTitle());
        commentDto.setContent(comment.getContent());
        commentDto.setCommentId(comment.getComment().getId());
        return commentDto;
    }
    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setActivity(activityRepository.findById(commentDto.getActivityId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Activity with id [%d] was not found!",
                        commentDto.getActivityId())
                ))
        );
        comment.setTitle(commentDto.getTitle());
        comment.setContent(commentDto.getContent());
        comment.setComment(commentRepository.findById(commentDto.getCommentId()).orElse(null));
        return comment;
    }
}
