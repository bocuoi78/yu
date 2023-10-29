package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.service.StudentService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor= @__(@Autowired))
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponseDto> findAll() {
        List<StudentDto> studentDtoList = studentService.findAll();
        if (!studentDtoList.isEmpty()) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(studentDtoList)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } else {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Failed").data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> detail(
            @PathVariable(name = "id") String id
    ) {
        try {
            StudentDto studentDtoResponse = studentService.detail(id);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(studentDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message(e.getMessage()).data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(
            @RequestBody StudentDto studentDtoRequest
    ) {
        try {
            StudentDto studentDtoResponse = studentService.create(studentDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(studentDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message(e.getMessage()).data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> edit(
            @RequestBody StudentDto studentDtoRequest
    ) {
        try {
            StudentDto studentDtoResponse = studentService.edit(studentDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(studentDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message(e.getMessage()).data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "id") String id
    ) {
        try {
            studentService.remove(id);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message(e.getMessage()).data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
