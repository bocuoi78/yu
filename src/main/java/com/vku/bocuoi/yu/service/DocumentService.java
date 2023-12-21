package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.DocumentDto;
import com.vku.bocuoi.yu.model.dto.request.DocumentRequestDto;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.response.UploadResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    ApiResponseDto getDocumentType(HttpServletRequest request);
    ApiResponseDto uploadDocument(HttpServletRequest request, MultipartFile file);
    ApiResponseDto saveDocument(HttpServletRequest request, DocumentDto documentDto);
    ApiResponseDto getDocumentSend(HttpServletRequest request, DocumentRequestDto documentRequestDto);
    ApiResponseDto getDocumentReceive(HttpServletRequest request, DocumentRequestDto documentRequestDto);
    Resource getDocument(HttpServletRequest request, Long id);
    ApiResponseDto sendDocument(HttpServletRequest request, Long documentId, Long organizationId);
    ApiResponseDto recallDocument(HttpServletRequest request, Long documentId, Long organizationId);
    ApiResponseDto logDocumentSent(HttpServletRequest request, Long documentId, Long organizationId);
    UploadResponseDto getDocumentUploadResponseDto(HttpServletRequest request, Long id);
    ApiResponseDto deleteDocument(HttpServletRequest request, Long id);
}
