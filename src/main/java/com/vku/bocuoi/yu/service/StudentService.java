package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.ChangePasswordDto;
import com.vku.bocuoi.yu.model.dto.StudentDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    ApiResponseDto findAll(HttpServletRequest request);
    ApiResponseDto detail(HttpServletRequest request, String id);
    ApiResponseDto create(HttpServletRequest request, StudentDto studentDto);
    ApiResponseDto edit(HttpServletRequest request, StudentDto studentDto);
    ApiResponseDto remove(HttpServletRequest request, String id);
    InputStreamResource export(HttpServletRequest request) throws Exception;
    ApiResponseDto importData(HttpServletRequest request, MultipartFile importFile);
    ApiResponseDto changePassword(HttpServletRequest request, ChangePasswordDto changePasswordDto);
    Resource getStudentImage(HttpServletRequest request, String id);
    Resource uploadStudentImage(HttpServletRequest request, String id, MultipartFile file);
    StudentDto getStudent(String id);

    String saveResetPasswordCode(String id);

    Boolean resetPassword(String token);
}