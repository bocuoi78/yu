package com.vku.bocuoi.yu.model.dto.response;

import com.vku.bocuoi.yu.model.dto.DocumentDto;
import lombok.Data;

import java.util.List;

@Data
public class DocumentResponseDto {
    private List<DocumentDto> dataPaging;
    private Long total;
}
