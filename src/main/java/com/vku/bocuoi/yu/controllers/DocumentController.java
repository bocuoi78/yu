package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.DocumentDto;
import com.vku.bocuoi.yu.model.dto.request.DocumentRequestDto;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.service.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/get-document-type")
    public ResponseEntity<ApiResponseDto> getDocumentType(
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(documentService.getDocumentType(request), HttpStatus.OK);
    }

    @PostMapping("/upload")
    ResponseEntity<ApiResponseDto> uploadDocument(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file
    ) {
        return new ResponseEntity<>(documentService.uploadDocument(request, file), HttpStatus.OK);
    }

    @PostMapping("/save")
    ResponseEntity<ApiResponseDto> saveDocument(
            HttpServletRequest request,
            @RequestBody DocumentDto documentDto
    ) {
        return new ResponseEntity<>(documentService.saveDocument(request, documentDto), HttpStatus.OK);
    }

    @PostMapping("/get-list-document-send")
    ResponseEntity<ApiResponseDto> getDocumentSend(
            HttpServletRequest request,
            @RequestBody DocumentRequestDto documentRequestDto
    ) {
        return new ResponseEntity<>(documentService.getDocumentSend(request, documentRequestDto), HttpStatus.OK);
    }

    @PostMapping("/get-list-document-receive")
    ResponseEntity<ApiResponseDto> getDocumentReceive(
            HttpServletRequest request,
            @RequestBody DocumentRequestDto documentRequestDto
    ) {
        return new ResponseEntity<>(documentService.getDocumentReceive(request, documentRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Resource> viewDocument(
            HttpServletRequest request,
            @PathVariable("id") Long id
    ) {
        String fileName = documentService.getDocumentUploadResponseDto(request, id).getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
//        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(documentService.getDocument(request, id), headers, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    ResponseEntity<Resource> downloadDocument(
            HttpServletRequest request,
            @PathVariable("id") Long id
    ) {
        String fileName = documentService.getDocumentUploadResponseDto(request, id).getFileName();
        fileName = UriUtils.encode(fileName, StandardCharsets.UTF_8.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(documentService.getDocument(request, id), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponseDto> deleteDocument(
            HttpServletRequest request,
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(documentService.deleteDocument(request, id), HttpStatus.OK);
    }

    @PostMapping("/send")
    ResponseEntity<ApiResponseDto> sendDocument(
            HttpServletRequest request,
            @RequestParam("documentId") Long documentId,
            @RequestParam("organizationId") Long organizationId
    ) {
        return new ResponseEntity<>(documentService.sendDocument(request, documentId, organizationId), HttpStatus.OK);
    }

    @PostMapping("/recall")
    ResponseEntity<ApiResponseDto> recallDocument(
            HttpServletRequest request,
            @RequestParam("documentId") Long documentId,
            @RequestParam("organizationId") Long organizationId
    ) {
        return new ResponseEntity<>(documentService.recallDocument(request, documentId, organizationId), HttpStatus.OK);
    }

    @GetMapping("/log-sent")
    ResponseEntity<ApiResponseDto> logDocumentSent(
            HttpServletRequest request,
            @RequestParam("documentId") Long documentId,
            @RequestParam("organizationId") Long organizationId
    ) {
        return new ResponseEntity<>(documentService.logDocumentSent(request, documentId, organizationId), HttpStatus.OK);
    }
}
