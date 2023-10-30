package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.StudentMapper;
import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(student -> StudentMapper.getInstance().toDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto detail(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Student with id [%d] was not found!", id)));
        return StudentMapper.getInstance().toDto(student);
    }

    @Override
    public StudentDto create(StudentDto studentDto) {
        Student newStudent = new Student();
        studentDto.setId(newStudent.getId());
        return StudentMapper.getInstance().toDto(
                studentRepository.save(StudentMapper.getInstance().toEntity(studentDto))
        );
    }

    @Override
    public StudentDto edit(StudentDto studentDto) {
        Student updateStudent = StudentMapper.getInstance().toEntity(studentDto);
        return StudentMapper.getInstance().toDto(studentRepository.save(updateStudent));
    }

    @Override
    public void remove(Long id) {
        Student deleteStudent = studentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Student with id [%d] was not found!", id)));
        studentRepository.delete(deleteStudent);
    }
}
