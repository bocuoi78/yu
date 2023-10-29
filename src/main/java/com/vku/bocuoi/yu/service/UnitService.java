package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.UnitDto;

import java.util.List;

public interface UnitService {
    List<UnitDto> findAll();
    UnitDto detail(Long id);
    UnitDto create(UnitDto unitDto);
    UnitDto edit(UnitDto unitDto);
    void remove(Long id);
}
