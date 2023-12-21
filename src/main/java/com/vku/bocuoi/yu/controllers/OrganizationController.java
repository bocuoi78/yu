package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.OrganizationDto;
import com.vku.bocuoi.yu.service.OrganizationService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor= @__(@Autowired))
@RequestMapping("/api/unit")
public class OrganizationController extends BaseController {
    private OrganizationService organizationService;
    @GetMapping
    public ResponseEntity<ApiResponseDto> findAll() {
        List<OrganizationDto> organizationDtoList = organizationService.findAll();
        if (!organizationDtoList.isEmpty()) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(organizationDtoList)
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
            @PathVariable(name = "id") Long id
    ) {
        try {
            OrganizationDto organizationDtoResponse = organizationService.detail(id);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(organizationDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Failed").data(e.getMessage())
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(
            @RequestBody OrganizationDto organizationDtoRequest
    ) {
        try {
            OrganizationDto organizationDtoResponse = organizationService.create(organizationDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(organizationDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Failed").data(e.getMessage())
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto> edit(
            @RequestBody OrganizationDto organizationDtoRequest
    ) {
        try {
            OrganizationDto organizationDtoResponse = organizationService.edit(organizationDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(organizationDtoResponse)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Failed").data(e.getMessage())
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> remove(
            @PathVariable(name = "id") Long id
    ) {
        try {
            organizationService.remove(id);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Failed").data(e.getMessage())
                    .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
