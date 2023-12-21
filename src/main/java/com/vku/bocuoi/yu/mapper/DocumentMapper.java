package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.DocumentDto;
import com.vku.bocuoi.yu.model.entity.Document;

public class DocumentMapper {
    private static DocumentMapper INSTANCE;
    public static DocumentMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DocumentMapper();
        }
        return INSTANCE;
    }
    public DocumentDto toDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(document.getId());
        documentDto.setNotation(document.getNotation());
        documentDto.setDocumentTypeId(document.getType().getId());
        documentDto.setName(document.getName());
        documentDto.setDateIssued(document.getDateIssued());
        documentDto.setOrganizationIssuedId(document.getOrganizationIssued().getId());
        documentDto.setFileName(document.getFileName());
        documentDto.setFileExtension(document.getFileExtension());
        documentDto.setFilePath(document.getFilePath());
        documentDto.setDescription(document.getDescription());
        return documentDto;
    }
}
