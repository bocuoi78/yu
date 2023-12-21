package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.StudentMapper;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.request.RequestDto;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.service.EmailService;
import com.vku.bocuoi.yu.service.PasswordResetService;
import com.vku.bocuoi.yu.service.StudentService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmailService emailService;
    @Override
    public ApiResponseDto requestPasswordReset(HttpServletRequest request, RequestDto requestDto) {
        ApiResponseDto apiResponseDto;
        String id = requestDto.getStudentId();
        Student student = StudentMapper.getInstance().toEntity(studentService.getStudent(id));
        String resetPasswordCode = studentService.saveResetPasswordCode(id);
        emailService.sendPasswordResetEmail(student.getEmail(), resetPasswordCode);
        apiResponseDto = ApiResponseDto.builder()
                .code(String.valueOf(HttpStatus.OK))
                .message("Check your VKU email for a code to reset your password")
                .data(null)
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto resetPassword(HttpServletRequest request, String code) {
        ApiResponseDto apiResponseDto;
        if (studentService.resetPassword(code)) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Your password reset successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Invalid of expired reset code")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }

        return apiResponseDto;
    }
}
