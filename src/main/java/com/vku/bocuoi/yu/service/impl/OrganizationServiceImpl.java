package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.OrganizationMapper;
import com.vku.bocuoi.yu.model.dto.OrganizationDto;
import com.vku.bocuoi.yu.model.entity.Organization;
import com.vku.bocuoi.yu.repository.OrganizationRepository;
import com.vku.bocuoi.yu.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    @Override
    public List<OrganizationDto> findAll() {
        return organizationRepository.findAll().stream()
                .map(unit -> OrganizationMapper.getInstance().toDto(unit))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto detail(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Unit with id [%d] was not found!", id)));
        return OrganizationMapper.getInstance().toDto(organization);
    }

    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        Organization newOrganization = new Organization();
        organizationDto.setId(newOrganization.getId());
        return OrganizationMapper.getInstance().toDto(
                organizationRepository.save(OrganizationMapper.getInstance().toEntity(organizationDto))
        );
    }

    @Override
    public OrganizationDto edit(OrganizationDto organizationDto) {
        Organization updateOrganization = OrganizationMapper.getInstance().toEntity(organizationDto);
        return OrganizationMapper.getInstance().toDto(organizationRepository.save(updateOrganization));
    }

    @Override
    public void remove(Long id) {
        Organization deleteOrganization = organizationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Unit with id [%d] was not found!", id)));
        organizationRepository.delete(deleteOrganization);
    }
}
