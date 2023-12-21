package com.vku.bocuoi.yu.model.dto;

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
public class OrganizationDto implements Serializable {
    private Long id;
    private String name;
    private Long organizationId;
    private Long organizationLevelId;
    private Set<StudentDto> studentDtoSet;
    private Set<ActivityDto> activityDtoSet;
}
