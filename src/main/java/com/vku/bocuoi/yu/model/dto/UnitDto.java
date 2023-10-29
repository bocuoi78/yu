package com.vku.bocuoi.yu.model.dto;

import com.vku.bocuoi.yu.model.entity.Activity;
import com.vku.bocuoi.yu.model.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long unitId;
//    private Set<UnitDto> unitDtoSet;
    private Set<StudentUnitDto> studentUnitDtoSet;
    private Set<DocumentProcessingDto> documentProcessingDtoSet;
    private Set<ActivityDto> activityDtoSet;
}
