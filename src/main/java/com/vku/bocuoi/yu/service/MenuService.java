package com.vku.bocuoi.yu.service;

import com.vku.bocuoi.yu.model.dto.MenuDto;

import java.util.List;

public interface MenuService {
    List<MenuDto> getMenuOfRoles();
    List<MenuDto> getMenuOfRoles(String id);
}
