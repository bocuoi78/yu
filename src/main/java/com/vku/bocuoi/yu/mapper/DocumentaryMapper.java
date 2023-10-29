package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.DocumentaryDto;
import com.vku.bocuoi.yu.model.entity.Documentary;

public class DocumentaryMapper {
    private static DocumentaryMapper INSTANCE;
    public static DocumentaryMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DocumentaryMapper();
        }
        return INSTANCE;
    }
    public DocumentaryDto toDto(Documentary documentary) {
        DocumentaryDto documentaryDto = new DocumentaryDto();
        documentaryDto.setId(documentary.getId());
        documentaryDto.setNumber(documentary.getNumber());
        documentaryDto.setDate(documentary.getDate());
        documentaryDto.setPlace(documentary.getPlace());
        documentaryDto.setAuthor(documentary.getAuthor());
        documentaryDto.setContent(documentary.getContent());
        documentaryDto.setFileName(documentary.getFileName());
        documentaryDto.setDescription(documentary.getDescription());
        return documentaryDto;
    }
    public Documentary toEntity(DocumentaryDto documentaryDto) {
        Documentary documentary = new Documentary();
        documentary.setId(documentaryDto.getId());
        documentary.setNumber(documentaryDto.getNumber());
        documentary.setDate(documentaryDto.getDate());
        documentary.setPlace(documentaryDto.getPlace());
        documentary.setAuthor(documentaryDto.getAuthor());
        documentary.setContent(documentaryDto.getContent());
        documentary.setFileName(documentaryDto.getFileName());
        documentary.setDescription(documentaryDto.getDescription());
        return documentary;
    }
}
