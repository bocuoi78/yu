package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.DocumentProcessingDto;
import com.vku.bocuoi.yu.model.entity.DocumentProcessing;
import com.vku.bocuoi.yu.repository.DocumentaryRepository;
import com.vku.bocuoi.yu.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;

public class DocumentProcessingMapper {
    DocumentaryRepository documentaryRepository;
    UnitRepository unitRepository;
    private static DocumentProcessingMapper INSTANCE;
    public static DocumentProcessingMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DocumentProcessingMapper();
        }
        return INSTANCE;
    }
    public DocumentProcessingDto toDto(DocumentProcessing documentProcessing) {
        DocumentProcessingDto documentProcessingDto = new DocumentProcessingDto();
        documentProcessingDto.setDocumentId(documentProcessing.getDocument().getId());
        documentProcessingDto.setUnitId(documentProcessing.getUnit().getId());
        documentProcessingDto.setIssuedDate(documentProcessing.getIssuedDate());
        documentProcessingDto.setDescription(documentProcessing.getDescription());
        return documentProcessingDto;
    }
    public DocumentProcessing toEntity(DocumentProcessingDto documentProcessingDto) {
        DocumentProcessing documentProcessing = new DocumentProcessing();
        documentProcessing.setDocument(documentaryRepository.findById(documentProcessingDto.getDocumentId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Document with id [%d] was not found!",
                        documentProcessingDto.getDocumentId())
                ))
        );
        documentProcessing.setUnit(unitRepository.findById(documentProcessingDto.getUnitId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Unit with id [%d] was not found!",
                        documentProcessingDto.getUnitId())
                ))
        );
        documentProcessing.setIssuedDate(documentProcessingDto.getIssuedDate());
        documentProcessing.setDescription(documentProcessingDto.getDescription());
        return documentProcessing;
    }
}
