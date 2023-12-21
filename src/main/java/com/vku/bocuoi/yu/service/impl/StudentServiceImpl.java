package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.auth.AuthenticationRequest;
import com.vku.bocuoi.yu.auth.RegisterRequest;
import com.vku.bocuoi.yu.mapper.StudentMapper;
import com.vku.bocuoi.yu.model.dto.response.ApiResponseDto;
import com.vku.bocuoi.yu.model.dto.ChangePasswordDto;
import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.AuthenticationService;
import com.vku.bocuoi.yu.service.FileService;
import com.vku.bocuoi.yu.service.JwtService;
import com.vku.bocuoi.yu.service.StudentService;
import com.vku.bocuoi.yu.utils.CommonConstants;
import com.vku.bocuoi.yu.utils.report.ExcelUtils;
import com.vku.bocuoi.yu.utils.report.FileFactory;
import com.vku.bocuoi.yu.config.excel.ImportConfig;
import com.vku.bocuoi.yu.utils.validate.ValidateObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private FileService fileService;
    private PasswordEncoder passwordEncoder;

    private final String DEFAULT_PASSWORD = "Abcd1234";

    @Override
    public ApiResponseDto findAll(HttpServletRequest request) {
        ApiResponseDto apiResponseDto;
        List<StudentDto> studentDtoList = studentRepository.findAll().stream()
                .map(student -> StudentMapper.getInstance().toDto(student))
                .collect(Collectors.toList());
        if(!studentDtoList.isEmpty()) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(studentDtoList)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto detail(HttpServletRequest request, String id) {
        ApiResponseDto apiResponseDto;
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(StudentMapper.getInstance().toDto(student))
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }
    @Override
    public ApiResponseDto create(HttpServletRequest request, StudentDto studentDto) {
        ApiResponseDto apiResponseDto;
        String jwt = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(jwt);
        try {
            Student student = studentRepository.save(StudentMapper.getInstance().toEntity(studentDto));
            student.setCreatedBy(authorId);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Created successfully")
                    .data(StudentMapper.getInstance().toDto(student))
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } catch (Exception e) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message(e.getLocalizedMessage())
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto edit(HttpServletRequest request, StudentDto studentDto) {
        ApiResponseDto apiResponseDto;
        String jwt = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(jwt);
        Map<String, String> errorValidator = ValidateObject.validateStudentDTO(studentDto);
        if (!ObjectUtils.isEmpty(errorValidator)) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message(null)
                    .data(errorValidator)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
            return apiResponseDto;
        }
        try {
            Student updateStudent = StudentMapper.getInstance().toEntity(studentDto);
            updateStudent.setPassword(studentRepository.findById(updateStudent.getId()).get().getPassword());
            updateStudent.setUpdatedBy(authorId);
            Student student = studentRepository.save(updateStudent);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Updated successfully")
                    .data(StudentMapper.getInstance().toDto(student))
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } catch (Exception e) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.BAD_REQUEST.toString()))
                    .message(e.getLocalizedMessage())
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto remove(HttpServletRequest request, String id) {
        ApiResponseDto apiResponseDto;
        String jwt = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(jwt);
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            student.setUpdatedBy(authorId);
            student.setIsDeleted(true);
//            studentRepository.delete(student);
            studentRepository.save(student);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Deleted successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        }
        return apiResponseDto;
    }

    @Override
    public InputStreamResource export(HttpServletRequest request) throws Exception {
        ApiResponseDto apiResponseDto;
        List<StudentDto> studentDtoList = studentRepository.findAll().stream()
                .map(student -> StudentMapper.getInstance().toDto(student))
                .collect(Collectors.toList());
        if(!studentDtoList.isEmpty()) {
            String fileName = "StudentExport" + ".xlsx";
            ByteArrayInputStream in = ExcelUtils.exportStudent(studentDtoList, fileName);
            InputStreamResource inputStreamResource = new InputStreamResource(in);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("Request successfully")
                    .data(inputStreamResource)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
            return inputStreamResource;
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.format(HttpStatus.NOT_FOUND.toString()))
                    .message("Empty")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
            return null;
        }
//        return apiResponseDto;
    }

    @Override
    public ApiResponseDto importData(HttpServletRequest request, MultipartFile importFile) {
        ApiResponseDto apiResponseDto;
        Workbook workbook = FileFactory.getWorkbookStream(importFile);
        List<StudentDto> studentDtoList = ExcelUtils.getImportData(workbook, ImportConfig.studentImport);
        if (!CollectionUtils.isEmpty(studentDtoList)) {
            saveData(studentDtoList);
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.OK))
                    .message("Import successfully")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        } else {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("Import failed")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_OK)
                    .build();
        }
        return apiResponseDto;
    }

    private void saveData(List<StudentDto> studentDtoList) {
        for (StudentDto studentDto : studentDtoList) {
            RegisterRequest request = new RegisterRequest(
                    studentDto.getId(),
                    studentDto.getName(),
                    studentDto.getBirthday(),
                    studentDto.getCId(),
                    studentDto.getPhone(),
                    "Abcd1234",
                    studentDto.getOrganizationId()
            );
            authenticationService.register(request);
        }
    }

    @Override
    public ApiResponseDto changePassword(HttpServletRequest request, ChangePasswordDto changePasswordDto) {
        ApiResponseDto apiResponseDto;
        String jwt = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(jwt);
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            apiResponseDto = ApiResponseDto.builder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
                    .message("New Password and Confirm Password do not match")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
                    .build();
        } else {
            Student student = studentRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Student not found"));
            if (passwordEncoder.matches(changePasswordDto.getOldPassword(), student.getPassword())) {
                authenticationService.authenticate(new AuthenticationRequest(student.getId(), changePasswordDto.getOldPassword()));
                student.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
                studentRepository.save(student);
                apiResponseDto = ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.OK))
                        .message("Changed password successfully")
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_OK)
                        .build();
            } else {
                apiResponseDto = ApiResponseDto.builder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST))
                        .message("Old Password is incorrect")
                        .data(null)
                        .status(CommonConstants.ApiStatus.STATUS_ERROR)
                        .build();
            }
        }
        return apiResponseDto;
    }

    @Override
    public Resource uploadStudentImage(HttpServletRequest request, String id, MultipartFile file) {
        String jwt = request.getHeader("Authorization").substring(7);
        String authorId = jwtService.extractStudentId(jwt);
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String encodedFileName = encodeFileName(originalFileName);
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        Resource resource = fileService.uploadImage(request, file, encodedFileName + fileExtension);
        student.setImage(encodedFileName + fileExtension);
        student.setUpdatedBy(authorId);
        studentRepository.save(student);
        return resource;
    }

    @Override
    public Resource getStudentImage(HttpServletRequest request, String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        String fileName = student.getImage();
        return fileService.getImage(fileName);
    }

    @Override
    public StudentDto getStudent(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return StudentMapper.getInstance().toDto(student);
    }

    @Override
    public String saveResetPasswordCode(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        String resetPasswordCode;
        do {
            resetPasswordCode = generateRandomCode();
        } while (studentRepository.findByResetPasswordCode(resetPasswordCode).isPresent());
        student.setResetPasswordCode(resetPasswordCode);
        student.setResetPasswordExpiry(new Date(System.currentTimeMillis() + 1000 * 60 + 5));
        studentRepository.save(student);
        return resetPasswordCode;
    }

    @Override
    public Boolean resetPassword(String code) {
        Student student = studentRepository.findByResetPasswordCode(code).orElseThrow(() -> new RuntimeException("Invalid of expired reset code"));
        if (resetPasswordCodeIsValid(code)) {
            student.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
            studentRepository.save(student);
            return true;
        } else {
            return false;
        }
    }

    private String encodeFileName(String originalFileName) {
        return DigestUtils.md5Hex(originalFileName + System.currentTimeMillis());
    }

    private String generateRandomCode() {
        int codeLength = 7;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int randomDigit = (int) (Math.random() * 10);
            code.append(randomDigit);
        }
        return code.toString();
    }
    
    private Boolean resetPasswordCodeIsValid(String code) {
        Optional<Student> student = studentRepository.findByResetPasswordCode(code);
        if (!student.isPresent()) {
            return false;
        } else {
            if (student.get().getResetPasswordExpiry().before(new Date())) {
                return false;
            } else {
                return true;
            }
        }
    }
}
