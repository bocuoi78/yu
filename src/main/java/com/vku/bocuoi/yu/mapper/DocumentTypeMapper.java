package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.DocumentTypeDto;
import com.vku.bocuoi.yu.model.entity.DocumentType;

public class DocumentTypeMapper {
    private static DocumentTypeMapper INSTANCE;
    public static DocumentTypeMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DocumentTypeMapper();
        }
        return INSTANCE;
    }
    public DocumentTypeDto toDto(DocumentType documentType) {
        DocumentTypeDto documentTypeDto = new DocumentTypeDto();
        documentTypeDto.setId(documentType.getId());
        documentTypeDto.setName(documentType.getName());
        return documentTypeDto;
    }
}
