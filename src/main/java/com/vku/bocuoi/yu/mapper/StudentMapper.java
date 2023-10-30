package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.model.dto.UnitDto;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.model.entity.Unit;

import java.util.stream.Collectors;

public class StudentMapper {
    private static StudentMapper INSTANCE;
    public static StudentMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StudentMapper();
        }
        return INSTANCE;
    }
    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setSId(student.getSId());
        studentDto.setName(student.getName());
        studentDto.setBirthday(student.getBirthday());
        studentDto.setGender(student.getGender());
        studentDto.setNation(student.getNation());
        studentDto.setCId(student.getCId());
        studentDto.setCIdDate(student.getCIdDate());
        studentDto.setCIdPlace(student.getCIdPlace());
        studentDto.setPhone(student.getPhone());
        studentDto.setEmail(student.getEmail());
        studentDto.setReligion(student.getReligion());
        studentDto.setEducationalLevel(student.getEducationalLevel());
        studentDto.setQualification(student.getQualification());
        studentDto.setPoliticalTheory(student.getPoliticalTheory());
        studentDto.setAddressPermanent(student.getAddressPermanent());
        studentDto.setAddressTemporary(student.getAddressTemporary());
        studentDto.setDateIn(student.getDateIn());
        studentDto.setPlaceIn(student.getPlaceIn());
        studentDto.setImage(student.getImage());
        studentDto.setPassword(student.getPassword());
        studentDto.setStudentUnitDtoSet(student.getStudentUnitSet().stream()
                .map(studentUnit -> StudentUnitMapper.getInstance().toDto(studentUnit))
                .collect(Collectors.toSet()));
        return studentDto;
    }
    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setSId(studentDto.getSId());
        student.setName(studentDto.getName());
        student.setBirthday(studentDto.getBirthday());
        student.setGender(studentDto.getGender());
        student.setNation(studentDto.getNation());
        student.setCId(studentDto.getCId());
        student.setCIdDate(studentDto.getCIdDate());
        student.setCIdPlace(studentDto.getCIdPlace());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());
        student.setReligion(studentDto.getReligion());
        student.setEducationalLevel(studentDto.getEducationalLevel());
        student.setQualification(studentDto.getQualification());
        student.setPoliticalTheory(studentDto.getPoliticalTheory());
        student.setAddressPermanent(studentDto.getAddressPermanent());
        student.setAddressTemporary(studentDto.getAddressTemporary());
        student.setDateIn(studentDto.getDateIn());
        student.setPlaceIn(studentDto.getPlaceIn());
        student.setImage(studentDto.getImage());
        student.setPassword(studentDto.getPassword());
        student.setStudentUnitSet(studentDto.getStudentUnitDtoSet().stream()
                .map(studentUnitDto -> StudentUnitMapper.getInstance().toEntity(studentUnitDto))
                .collect(Collectors.toSet()));
        return student;
    }
}
