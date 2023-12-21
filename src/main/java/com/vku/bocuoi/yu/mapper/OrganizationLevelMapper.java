package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.OrganizationLevelDto;
import com.vku.bocuoi.yu.model.entity.OrganizationLevel;

public class OrganizationLevelMapper {
    private static OrganizationLevelMapper INSTANCE;
    public static OrganizationLevelMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrganizationLevelMapper();
        }
        return INSTANCE;
    }
    public OrganizationLevelDto toDto(OrganizationLevel organizationLevel) {
        OrganizationLevelDto organizationLevelDto = new OrganizationLevelDto();
        organizationLevelDto.setId(organizationLevel.getId());
        organizationLevelDto.setName(organizationLevel.getName());
        return organizationLevelDto;
    }
    public OrganizationLevel toEntity(OrganizationLevelDto organizationLevelDto) {
        OrganizationLevel organizationLevel = new OrganizationLevel();
        organizationLevel.setId(organizationLevelDto.getId());
        organizationLevel.setName(organizationLevelDto.getName());
        return organizationLevel;
    }
}
