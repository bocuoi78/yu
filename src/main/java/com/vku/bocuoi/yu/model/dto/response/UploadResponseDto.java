package com.vku.bocuoi.yu.model.dto.response;

import lombok.Data;

@Data
public class UploadResponseDto {
    private String fileName;
    private String fileExtension;
    private String filePath;
}
