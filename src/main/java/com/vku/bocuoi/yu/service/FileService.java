package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.response.UploadResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Resource uploadImage(HttpServletRequest request, MultipartFile file, String fileName);
    Resource getImage(String fileName);
    String uploadFile(MultipartFile file);
    Resource getFile(UploadResponseDto uploadResponseDto);
}
