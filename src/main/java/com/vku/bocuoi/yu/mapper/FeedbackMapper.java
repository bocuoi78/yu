package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.FeedbackDto;
import com.vku.bocuoi.yu.model.entity.Feedback;
import com.vku.bocuoi.yu.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

public class FeedbackMapper {
    StudentRepository studentRepository;

    public static CommentMapper INSTANCE;
    public static CommentMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CommentMapper();
        }
        return INSTANCE;
    }

    public FeedbackDto toDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setStudentId(feedback.getStudent().getId());
        feedbackDto.setTitle(feedback.getTitle());
        feedbackDto.setContent(feedback.getContent());
        return feedbackDto;
    }
    public Feedback toEntity(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setStudent(studentRepository.findById(feedbackDto.getStudentId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Student with id [%s] was not found!",
                        feedbackDto.getStudentId())
                ))
        );
        feedback.setTitle(feedbackDto.getTitle());
        feedback.setContent(feedbackDto.getContent());
        return feedback;
    }
}
