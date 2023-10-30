package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto detail(Long id);
    StudentDto create(StudentDto studentDto);
    StudentDto edit(StudentDto studentDto);
    void remove(Long id);
}