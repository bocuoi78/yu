package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.UnitDto;
import com.vku.bocuoi.yu.model.entity.Unit;

import java.util.stream.Collectors;

public class UnitMapper {
    private static UnitMapper INSTANCE;
    public static UnitMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UnitMapper();
        }
        return INSTANCE;
    }
    public UnitDto toDto(Unit unit) {
        UnitDto unitDto = new UnitDto();
        unitDto.setId(unit.getId());
        unitDto.setName(unit.getName());
        unitDto.setDescription(unit.getDescription());
        if(unit.getUnitId() != null) {
            unitDto.setUnitId(unit.getUnitId());
        }
        if(unit.getStudentUnitSet()!=null) {
            unitDto.setStudentUnitDtoSet(unit.getStudentUnitSet().stream()
                    .map(studentUnit -> StudentUnitMapper.getInstance().toDto(studentUnit))
                    .collect(Collectors.toSet()));
        }
        return unitDto;
    }
    public Unit toEntity(UnitDto unitDto) {
        Unit unit = new Unit();
        unit.setId(unitDto.getId());
        unit.setName(unitDto.getName());
        unit.setDescription(unitDto.getDescription());
        if(unitDto.getUnitId()!=null) {
            unit.setUnitId(unitDto.getUnitId());
        }
        if(unitDto.getStudentUnitDtoSet()!=null) {
            unit.setStudentUnitSet(unitDto.getStudentUnitDtoSet().stream()
                    .map(studentUnitDto -> StudentUnitMapper.getInstance().toEntity(studentUnitDto))
                    .collect(Collectors.toSet()));
        }
        return unit;
    }
}
