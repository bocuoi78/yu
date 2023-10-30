package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.auth.AuthenticationRequest;
import com.vku.bocuoi.yu.auth.AuthenticationResponse;
import com.vku.bocuoi.yu.auth.RegisterRequest;
import com.vku.bocuoi.yu.model.entity.Role;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.AuthenticationService;
import com.vku.bocuoi.yu.service.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var student = Student.builder()
                .sId(request.getStudentId())
                .name(request.getName())
                .cId(request.getCId())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
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
        var student = studentRepository.findStudentBysId(request.getStudentId())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
