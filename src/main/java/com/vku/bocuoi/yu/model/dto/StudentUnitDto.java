package com.vku.bocuoi.yu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentUnitDto implements Serializable {
    private Long studentId;
    private Long unitId;
    private Integer position;
    private String positionName;
}
