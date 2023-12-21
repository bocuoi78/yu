package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.mapper.MenuMapper;
import com.vku.bocuoi.yu.model.dto.MenuDto;
import com.vku.bocuoi.yu.model.entity.Role;
import com.vku.bocuoi.yu.model.entity.Student;
import com.vku.bocuoi.yu.repository.MenuRepository;
import com.vku.bocuoi.yu.repository.RoleRepository;
import com.vku.bocuoi.yu.repository.StudentRepository;
import com.vku.bocuoi.yu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private MenuRepository menuRepository;
    @Override
    public List<MenuDto> getMenuOfRoles() {
        List<Role> guestRole = new ArrayList<>();
        guestRole.add(roleRepository.findById(21L).get());
        return menuRepository.findAllByRoleListIn(guestRole).stream()
                .map(menu -> MenuMapper.getInstance().toDto(menu))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> getMenuOfRoles(String id) {
        Student student = studentRepository.findById(id).get();
        List<Role> roles = student.getRoleSet().stream().toList();
        return menuRepository.findAllByRoleListIn(roles).stream()
                .map(menu -> MenuMapper.getInstance().toDto(menu))
                .collect(Collectors.toList());
    }
}
