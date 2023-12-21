package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.ChangePasswordDto;
import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.service.FileService;
import com.vku.bocuoi.yu.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private FileService fileService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '20')")
    public ResponseEntity<ApiResponseDto> findAll(
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.findAll(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<ApiResponseDto> detail(
            @PathVariable(name = "id") String id,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.detail(request, id), HttpStatus.OK);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Resource> uploadAvatar(
            @PathVariable(name = "id") String id,
            @RequestParam("file")MultipartFile imageFile,
            HttpServletRequest request
    ) {
        Resource resource = studentService.uploadStudentImage(request, id, imageFile);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
//    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<Resource> getStudentImage(
            @PathVariable(name = "id") String id,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.getStudentImage(request, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(
            @RequestBody StudentDto studentDtoRequest,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.create(request, studentDtoRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> edit(
            @RequestBody StudentDto studentDtoRequest,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.edit(request, studentDtoRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "id") String id,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(studentService.remove(request, id), HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> export(
            HttpServletRequest request
    ) throws Exception {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + URLEncoder.encode("StudentExport" + ".xlsx", StandardCharsets.UTF_8)
                )
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel; charset=UTF-8"))
                .body(studentService.export(request));
    }

    @PostMapping("/import")
    public ResponseEntity<ApiResponseDto> importData(
            @RequestParam("file")MultipartFile importFile,
            HttpServletRequest request
            ) {
        ApiResponseDto apiResponseDto = studentService.importData(request, importFile);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ApiResponseDto> ChangePassword(
            HttpServletRequest request,
            @RequestBody ChangePasswordDto changePasswordDto
            ) {
        ApiResponseDto apiResponseDto = studentService.changePassword(request, changePasswordDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
