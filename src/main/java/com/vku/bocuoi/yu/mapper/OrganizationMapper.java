package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.OrganizationDto;
import com.vku.bocuoi.yu.model.entity.Organization;
import org.springframework.util.ObjectUtils;

public class OrganizationMapper {
    private static OrganizationMapper INSTANCE;
    public static OrganizationMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrganizationMapper();
        }
        return INSTANCE;
    }
    public OrganizationDto toDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setName(organization.getName());
        if (!ObjectUtils.isEmpty(organization.getOrganizationId())) {
            organizationDto.setOrganizationId(organization.getOrganizationId());
        }
        organizationDto.setOrganizationLevelId(organization.getOrganizationLevelId());
        return organizationDto;
    }
    public Organization toEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        if (!ObjectUtils.isEmpty(organizationDto.getOrganizationId())) {
            organization.setOrganizationId(organizationDto.getOrganizationId());
        }
        organization.setOrganizationLevelId(organizationDto.getOrganizationLevelId());
        return organization;
    }
}
