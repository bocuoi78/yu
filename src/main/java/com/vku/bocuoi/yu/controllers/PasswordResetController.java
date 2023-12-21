package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.request.RequestDto;
import com.vku.bocuoi.yu.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password-reset")
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponseDto> requestPasswordReset(
            HttpServletRequest request,
            @RequestBody RequestDto requestDto
    ) {
        ApiResponseDto apiResponseDto = passwordResetService.requestPasswordReset(request, requestDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<ApiResponseDto> resetPassword(
            HttpServletRequest request,
            @RequestParam String code
    ) {
        ApiResponseDto apiResponseDto = passwordResetService.resetPassword(request, code);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
