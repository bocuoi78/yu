package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> findAll();
    OrganizationDto detail(Long id);
    OrganizationDto create(OrganizationDto organizationDto);
    OrganizationDto edit(OrganizationDto organizationDto);
    void remove(Long id);
}
