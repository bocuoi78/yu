package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.MenuDto;
import com.vku.bocuoi.yu.model.entity.Menu;
import com.vku.bocuoi.yu.repository.MenuRepository;

public class MenuMapper {
    MenuRepository menuRepository;
    private static MenuMapper INSTANCE;
    public static MenuMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MenuMapper();
        }
        return INSTANCE;
    }
    public MenuDto toDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setMenuId(menu.getMenu().getId());
        menuDto.setName(menu.getName());
        menuDto.setDescription(menu.getDescription());
        return menuDto;
    }
    public Menu toEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setMenu(menuRepository.findById(menuDto.getMenuId()).orElse(null));
        menu.setId(menuDto.getId());
        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());
        return menu;
    }
}
