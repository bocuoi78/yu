package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.StudentDto;
import com.vku.bocuoi.yu.model.entity.Student;

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
        studentDto.setName(student.getName());
        studentDto.setGender(student.getGender());
        studentDto.setBirthday(student.getBirthday());
        studentDto.setCId(student.getCId());
        studentDto.setCIdDate(student.getCIdDate());
        studentDto.setCIdPlace(student.getCIdPlace());
        studentDto.setHometown(student.getHometown());
        studentDto.setAddressPermanent(student.getAddressPermanent());
        studentDto.setNationality(student.getNationality());
        studentDto.setNation(student.getNation());
        studentDto.setReligion(student.getReligion());
        studentDto.setEducationalLevel(student.getEducationalLevel());
        studentDto.setQualification(student.getQualification());
        studentDto.setPoliticalTheory(student.getPoliticalTheory());
        studentDto.setDateIn(student.getDateIn());
        studentDto.setPlaceIn(student.getPlaceIn());
        studentDto.setPhone(student.getPhone());
        studentDto.setEmail(student.getEmail());
        studentDto.setFacebook(student.getFacebook());
        studentDto.setOrganizationId(student.getOrganizationId());
        studentDto.setRoleDtoSet(student.getRoleSet().stream()
                .map(role -> RoleMapper.getInstance().toDto(role))
                .collect(Collectors.toSet()));
        return studentDto;
    }
    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setGender(studentDto.getGender());
        student.setBirthday(studentDto.getBirthday());
        student.setCId(studentDto.getCId());
        student.setCIdDate(studentDto.getCIdDate());
        student.setCIdPlace(studentDto.getCIdPlace());
        student.setHometown(studentDto.getHometown());
        student.setAddressPermanent(studentDto.getAddressPermanent());
        student.setNationality(studentDto.getNationality());
        student.setNation(studentDto.getNation());
        student.setReligion(studentDto.getReligion());
        student.setEducationalLevel(studentDto.getEducationalLevel());
        student.setQualification(studentDto.getQualification());
        student.setPoliticalTheory(studentDto.getPoliticalTheory());
        student.setDateIn(studentDto.getDateIn());
        student.setPlaceIn(studentDto.getPlaceIn());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());
        student.setFacebook(studentDto.getFacebook());
        student.setOrganizationId(studentDto.getOrganizationId());
        student.setRoleSet(studentDto.getRoleDtoSet().stream()
                .map(roleDto -> RoleMapper.getInstance().toEntity(roleDto))
                .collect(Collectors.toSet()));
        return student;
    }
}
