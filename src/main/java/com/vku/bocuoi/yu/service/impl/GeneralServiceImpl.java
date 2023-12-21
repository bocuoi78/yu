package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.StudentMapper;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.UserInfoDto;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.GeneralService;
import com.vku.bocuoi.yu.service.JwtService;
import com.vku.bocuoi.yu.service.MenuService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private JwtService jwtService;
    @Override
    public ApiResponseDto getUserInfo(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        UserInfoDto userInfoDto = new UserInfoDto();
        if (request.getHeader("Authorization") == null) {
            userInfoDto.setNav(menuService.getMenuOfRoles());
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Successfully")
                    .data(userInfoDto)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
            return apiResponseDto;
        }
        String token = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(token);
        if (studentRepository.findById(authorId).isPresent()) {
            try {
                Student student = studentRepository.findById(authorId).get();
                userInfoDto.setProfile(StudentMapper.getInstance().toDto(student));
                userInfoDto.setNav(menuService.getMenuOfRoles(authorId));
                apiResponseDto = ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.OK))
                        .message("Successfully")
                        .data(userInfoDto)
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                apiResponseDto = ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST))
                        .message(e.getLocalizedMessage())
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_ERROR)
                        .build();
            }
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.UNAUTHORIZED.toString()))
                    .message("Unvalid token")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }
}
