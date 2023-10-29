package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.UnitMapper;
import com.vku.bocuoi.yu.model.dto.UnitDto;
import com.vku.bocuoi.yu.model.entity.Unit;
import com.vku.bocuoi.yu.repository.UnitRepository;
import com.vku.bocuoi.yu.service.UnitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UnitServiceImpl implements UnitService {
    private UnitRepository unitRepository;
    @Override
    public List<UnitDto> findAll() {
        return unitRepository.findAll().stream()
                .map(unit -> UnitMapper.getInstance().toDto(unit))
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto detail(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Unit with id [%d] was not found!", id)));
        return UnitMapper.getInstance().toDto(unit);
    }

    @Override
    public UnitDto create(UnitDto unitDto) {
        Unit newUnit = new Unit();
        unitDto.setId(newUnit.getId());
        return UnitMapper.getInstance().toDto(
                unitRepository.save(UnitMapper.getInstance().toEntity(unitDto))
        );
    }

    @Override
    public UnitDto edit(UnitDto unitDto) {
        Unit updateUnit = UnitMapper.getInstance().toEntity(unitDto);
        return UnitMapper.getInstance().toDto(unitRepository.save(updateUnit));
    }

    @Override
    public void remove(Long id) {
        Unit deleteUnit = unitRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Unit with id [%d] was not found!", id)));
        unitRepository.delete(deleteUnit);
    }
}
