package com.vku.bocuoi.yu.controllers;

import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.service.GeneralService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/general")
public class GeneralController {
    @Autowired
    private GeneralService generalService;

    @GetMapping("/getUserInfo")
    public ResponseEntity<ApiResponseDto> getUserInfo(
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(generalService.getUserInfo(request), HttpStatus.OK);
    }
}
