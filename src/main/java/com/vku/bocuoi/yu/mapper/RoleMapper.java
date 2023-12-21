package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.RoleDto;
import com.vku.bocuoi.yu.model.entity.Role;

public class RoleMapper {
    private static RoleMapper INSTANCE;
    public static RoleMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RoleMapper();
        }
        return INSTANCE;
    }
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        roleDto.setStatus(role.getStatus());
//        roleDto.setStudentSet(role.getStudentSet());
        return roleDto;
    }
    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setStatus(roleDto.getStatus());
//        role.setStudentSet(roleDto.getStudentSet());
        return role;
    }
}
