package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.DocumentMapper;
import com.vku.bocuoi.yu.mapper.DocumentTypeMapper;
import com.vku.bocuoi.yu.mapper.OrganizationMapper;
import com.vku.bocuoi.yu.model.dto.DocumentDto;
import com.vku.bocuoi.yu.model.dto.OrganizationDto;
import com.vku.bocuoi.yu.model.dto.request.DocumentRequestDto;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.DocumentTypeDto;
import com.vku.bocuoi.yu.model.dto.response.DocumentResponseDto;
import com.vku.bocuoi.yu.model.dto.response.UploadResponseDto;
import com.vku.bocuoi.yu.model.entity.Document;
import com.vku.bocuoi.yu.model.entity.Organization;
import com.vku.bocuoi.yu.repository.DocumentRepository;
import com.vku.bocuoi.yu.repository.DocumentTypeRepository;
import com.vku.bocuoi.yu.repository.OrganizationRepository;
import com.vku.bocuoi.yu.service.DocumentService;
import com.vku.bocuoi.yu.service.FileService;
import com.vku.bocuoi.yu.service.JwtService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public ApiResponseDto getDocumentType(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        try {
            List<DocumentTypeDto> documentTypeDtoList = documentTypeRepository.findAll()
                    .stream()
                    .map((documentType -> DocumentTypeMapper.getInstance().toDto(documentType)))
                    .toList();
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Successfully")
                    .data(documentTypeDtoList)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } catch (Exception e) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message(e.getLocalizedMessage())
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto uploadDocument(HttpServletRequest request, MultipartFile file) {
        ApiResponseDto apiResponseDto;
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "";
        UploadResponseDto uploadResponseDto = new UploadResponseDto();
        uploadResponseDto.setFileName(fileName);
        uploadResponseDto.setFileExtension(fileExtension);
        if (!(filePath = fileService.uploadFile(file)).isEmpty()) {
            uploadResponseDto.setFilePath(filePath);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Upload successfully")
                    .data(uploadResponseDto)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Upload failed")
                    .data(uploadResponseDto)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto saveDocument(HttpServletRequest request, DocumentDto documentDto) {
        ApiResponseDto apiResponseDto;
        Document document;
        if (Objects.isNull(documentDto.getId())) {
            document = new Document();
        } else {
            document = documentRepository.findById(documentDto.getId()).orElse(new Document());
        }
        document.setNotation(documentDto.getNotation());
        document.setType(documentTypeRepository.findById(
                documentDto.getDocumentTypeId()).orElse(null));
        document.setName(documentDto.getName());
        document.setDateIssued(documentDto.getDateIssued());
        document.setOrganizationIssued(organizationRepository.findById(
                documentDto.getOrganizationIssuedId()).orElse(null));
        document.setFileName(documentDto.getFileName());
        document.setFileExtension(documentDto.getFileExtension());
        document.setFilePath(documentDto.getFilePath());
        document.setDescription(documentDto.getDescription());
        apiResponseDto = ApiResponseDto.builder()
                .code(String.valueOf(HttpStatus.OK))
                .message("Upload successfully")
                .data(
                        DocumentMapper.getInstance().toDto(
                                documentRepository.save(document)
                        )
                )
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();
        if (Objects.isNull(document.getType())) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Document type not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto getDocumentSend(HttpServletRequest request, DocumentRequestDto documentRequestDto) {
        Pageable pageable = PageRequest.of(
                documentRequestDto.getCurrentPage().intValue(),
                documentRequestDto.getMaxSize().intValue(),
                Sort.by("dateIssued").descending()
        );
        Page<Document> document = documentRepository.findDocumentSendByCriteria(
                documentRequestDto.getOrganizationId(),
                documentRequestDto.getKeyword(),
                documentRequestDto.getDocumentTypeId(),
                documentRequestDto.getFromDate(),
                documentRequestDto.getToDate(),
                pageable
        );
        DocumentResponseDto documentResponseDto = new DocumentResponseDto();
        documentResponseDto.setDataPaging(document.stream().
                map(doc -> DocumentMapper.getInstance().toDto(doc)).toList());
        documentResponseDto.setTotal(document.getTotalElements());
        return ApiResponseDto.builder()
                .code(String.valueOf(HttpStatus.OK))
                .message("Successfully")
                .data(documentResponseDto)
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();
    }

    @Override
    public ApiResponseDto getDocumentReceive(HttpServletRequest request, DocumentRequestDto documentRequestDto) {
        Pageable pageable = PageRequest.of(
                documentRequestDto.getCurrentPage().intValue(),
                documentRequestDto.getMaxSize().intValue(),
                Sort.by("dateIssued").descending()
        );
        Page<Document> document = documentRepository.findDocumentReceiveByCriteria(
                documentRequestDto.getOrganizationId(),
                documentRequestDto.getKeyword(),
                documentRequestDto.getDocumentTypeId(),
                documentRequestDto.getFromDate(),
                documentRequestDto.getToDate(),
                pageable
        );
        DocumentResponseDto documentResponseDto = new DocumentResponseDto();
        documentResponseDto.setDataPaging(document.stream().
                map(doc -> DocumentMapper.getInstance().toDto(doc)).toList());
        documentResponseDto.setTotal(document.getTotalElements());
        return ApiResponseDto.builder()
                .code(String.valueOf(HttpStatus.OK))
                .message("Successfully")
                .data(documentResponseDto)
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();
    }

    @Override
    public Resource getDocument(HttpServletRequest request, Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            UploadResponseDto uploadResponseDto = new UploadResponseDto();
            uploadResponseDto.setFileName(document.get().getFileName());
            uploadResponseDto.setFileExtension(document.get().getFileExtension());
            uploadResponseDto.setFilePath(document.get().getFilePath());
            return fileService.getFile(uploadResponseDto);
        }
        return null;
    }

    @Override
    public ApiResponseDto deleteDocument(HttpServletRequest request, Long id) {
        ApiResponseDto apiResponseDto;
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setIsDeleted(true);
            documentRepository.save(document);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Delete successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Document not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto sendDocument(HttpServletRequest request, Long documentId, Long organizationId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<Organization> organizationReceived = documentRepository.findOrganizationInDocumentById(organizationId);
            if (organizationReceived.isEmpty()) {
                Optional<Organization> organizationReceive = organizationRepository.findById(organizationId);
                organizationReceive.ifPresent(organization -> document.getOrganizationList().add(organization));
            }
            documentRepository.save(document);
            return ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Send successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            return ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Document not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
    }

    @Override
    public ApiResponseDto recallDocument(HttpServletRequest request, Long documentId, Long organizationId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<Organization> organizationOptional = organizationRepository.findById(organizationId);
            if (organizationOptional.isPresent()) {
                Organization organization = organizationOptional.get();
                List<Organization> organizationList = new ArrayList<>(document.getOrganizationList());
                int i = 0;
                for (; i < organizationList.size(); i++) {
                    if (Objects.equals(organizationList.get(i).getId(), organization.getId())) {
                        organizationList.remove(i);
                        break;
                    }
                }
                document.setOrganizationList(organizationList);
                documentRepository.save(document);
                return ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.OK))
                        .message("Recall successfully")
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            } else {
                return ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST))
                        .message("Organization not found")
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_ERROR)
                        .build();
            }
        } else {
            return ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Document not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
    }

    @Override
    public ApiResponseDto logDocumentSent(HttpServletRequest request, Long documentId, Long organizationId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        List<Organization> organizationList = organizationRepository.findAllOrganizationChildById(organizationId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Set<Organization> organizationSentList = new HashSet<>(document.getOrganizationList());
            List<OrganizationDto> logResult = organizationList.stream().filter(organizationSentList::contains).toList()
                    .stream().map(
                            organization -> OrganizationMapper.getInstance().toDto(organization)
                    ).collect(Collectors.toList());
            return ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Successfully")
                    .data(logResult)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            return ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Document not found")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
    }

    @Override
    public UploadResponseDto getDocumentUploadResponseDto(HttpServletRequest request, Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            UploadResponseDto uploadResponseDto = new UploadResponseDto();
            uploadResponseDto.setFileName(document.get().getFileName());
            uploadResponseDto.setFileExtension(document.get().getFileExtension());
            uploadResponseDto.setFilePath(document.get().getFilePath());
            return uploadResponseDto;
        }
        return null;
    }
}
