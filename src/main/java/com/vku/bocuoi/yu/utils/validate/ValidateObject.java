package com.vku.bocuoi.yu.utils.validate;

import com.vku.bocuoi.yu.model.dto.StudentDto;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {
    public static Map<String, String> validateStudentDTO(StudentDto studentDto) {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(ValidateUtils.builder()
                .fieldName("name")
                .value(studentDto.getName())
                .required(true)
                .build().validate());
        errors.putAll(ValidateUtils.builder()
                .fieldName("phone")
                .value(studentDto.getPhone())
                .required(true)
                .build().validate());
        errors.putAll(ValidateUtils.builder()
                .fieldName("cid")
                .value(studentDto.getCId())
                .required(true)
                .build().validate());
        return errors;
    }
//    public static Map<String, String> validatePostDTO(PostDto dto){
//        Map<String, String> errors = new HashMap<>();
//        errors.putAll(ValidateUtils.builder()
//                .fieldName("title")
//                .value(dto.getTitle())
//                .required(true)
//                .maxLength(20)
//                .build().validate());
//        errors.putAll(ValidateUtils.builder()
//                .fieldName("description")
//                .value(dto.getDescription())
//                .required(false)
//                .maxLength(50)
//                .build().validate());
//        errors.putAll(ValidateUtils.builder()
//                .fieldName("maximumOfComments")
//                .value(dto.getMaximumOfComments())
//                .required(true)
//                .isInteger(true)
//                .min(Long.valueOf(1))
//                .max(Long.valueOf(10))
//                .build().validate());
//        errors.putAll(ValidateUtils.builder()
//                .fieldName("content")
//                .value(dto.getContent())
//                .required(true)
//                .maxLength(30)
//                .build().validate());
//        errors.putAll(ValidateUtils.builder()
//                .fieldName("phoneNumber")
//                .value(dto.getPhoneNumber())
//                .required(true)
//                .maxLength(10)
//                .onlyNumber(true)
//                .build().validate());
//        return errors;
//    }
}
