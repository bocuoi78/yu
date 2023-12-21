package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface GeneralService {
    ApiResponseDto getUserInfo(HttpServletRequest request);
}
