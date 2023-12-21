package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.request.RequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface PasswordResetService {
    ApiResponseDto requestPasswordReset(HttpServletRequest request, RequestDto requestDto);
    ApiResponseDto resetPassword(HttpServletRequest request, String code);
}
