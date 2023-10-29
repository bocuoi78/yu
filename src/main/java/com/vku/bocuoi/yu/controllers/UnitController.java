package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.UnitDto;
import com.vku.bocuoi.yu.service.UnitService;
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
public class UnitController extends BaseController {
    private UnitService unitService;
    @GetMapping
    public ResponseEntity<ApiResponseDto> findAll() {
        List<UnitDto> unitDtoList = unitService.findAll();
        if (!unitDtoList.isEmpty()) {
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(unitDtoList)
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
            UnitDto unitDtoResponse = unitService.detail(id);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(unitDtoResponse)
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
            @RequestBody UnitDto unitDtoRequest
    ) {
        try {
            UnitDto unitDtoResponse = unitService.create(unitDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(unitDtoResponse)
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
            @RequestBody UnitDto unitDtoRequest
    ) {
        try {
            UnitDto unitDtoResponse = unitService.edit(unitDtoRequest);
            ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(null)
                    .message("Successfully").data(unitDtoResponse)
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
            unitService.remove(id);
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
