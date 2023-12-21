package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.auth.AuthenticationRequest;
import com.vku.bocuoi.yu.auth.AuthenticationResponse;
import com.vku.bocuoi.yu.auth.RegisterRequest;
import com.vku.bocuoi.yu.model.entity.Role;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.RoleRepository;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.AuthenticationService;
import com.vku.bocuoi.yu.service.EmailService;
import com.vku.bocuoi.yu.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Set<Role> roleSet = new HashSet<>();
        if (roleRepository.findById(18L).isPresent()) {
            Role defaultRole = roleRepository.findById(18L).get();
            roleSet.add(defaultRole);
        } else {
            System.out.println("hello");
        }

        var student = Student.builder()
                .id(request.getStudentId())
                .name(request.getName())
                .birthday(request.getBirthday())
                .cId(request.getCId())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .organizationId(request.getOrganizationId())
                .roleSet(roleSet)
                .build();
        studentRepository.save(student);
        var jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getStudentId(),
                        request.getPassword()
                )
        );
        var student = studentRepository.findById(request.getStudentId())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

//    @Override
//    public ApiResponseDto resetPassword() {
//        ApiResponseDto apiResponseDto;
//        try {
//            String header = "[YU VKU] Please reset your password";
//            String body = "<html>" +
//                    "<head></head>" +
//                    "<body>" +
//                    "<h1>Đặt lại mật khẩu</h1>" +
//                    "<p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. Để đặt lại mật khẩu của bạn, hãy nhấp vào liên kết sau:</p>" +
//                    "<a href=\"https://example.com/reset-password?token={token}\">Đặt lại mật khẩu</a>" +
//                    "<p>Liên kết này sẽ hết hạn sau 2 giờ.</p>" +
//                    "<p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>" +
//                    "</body>" +
//                    "</html>";
//            emailService.sendWithHTMLBody("ndkhanh.18it4@vku.udn.vn", header, body);
//            apiResponseDto = ApiResponseDto.builder()
//                    .code(String.valueOf(HttpStatus.OK))
//                    .message("Successfully")
//                    .data(null)
//                    .status(CommonConstants.ApiStatus.STATUS_OK)
//                    .build();
//        } catch (Exception e) {
//            apiResponseDto = ApiResponseDto.builder()
//                    .code(String.valueOf(HttpStatus.BAD_REQUEST))
//                    .message("Error")
//                    .data(e.getLocalizedMessage())
//                    .status(CommonConstants.ApiStatus.STATUS_ERROR)
//                    .build();
//        }
//        return apiResponseDto;
//    }
}
