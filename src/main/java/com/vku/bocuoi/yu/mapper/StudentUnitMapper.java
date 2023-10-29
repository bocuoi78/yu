package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.StudentUnitDto;
import com.vku.bocuoi.yu.model.entity.StudentUnit;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;

public class StudentUnitMapper {
    StudentRepository studentRepository;
    UnitRepository unitRepository;
    private static StudentUnitMapper INSTANCE;
    public static StudentUnitMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StudentUnitMapper();
        }
        return INSTANCE;
    }
    public StudentUnitDto toDto(StudentUnit studentUnit) {
        StudentUnitDto studentUnitDto = new StudentUnitDto();
        studentUnitDto.setStudentId(studentUnit.getStudent().getId());
        studentUnitDto.setUnitId(studentUnit.getUnit().getId());
        studentUnitDto.setPosition(studentUnit.getPosition());
        studentUnitDto.setPositionName(studentUnit.getPositionName());
        return studentUnitDto;
    }
    public StudentUnit toEntity(StudentUnitDto studentUnitDto) {
        StudentUnit studentUnit = new StudentUnit();
        studentUnit.setStudent(studentRepository.findById(studentUnitDto.getStudentId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Student with id [%s] was not found!",
                        studentUnitDto.getStudentId())
                ))
        );
        studentUnit.setUnit(unitRepository.findById(studentUnitDto.getUnitId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Unit with id [%d] was not found!",
                        studentUnitDto.getUnitId())
                ))
        );
        studentUnit.setPosition(studentUnitDto.getPosition());
        studentUnit.setPositionName(studentUnitDto.getPositionName());
        return studentUnit;
    }
}
