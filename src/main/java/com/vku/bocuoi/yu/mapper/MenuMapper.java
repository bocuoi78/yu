package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.MenuDto;
import com.vku.bocuoi.yu.model.entity.Menu;
import com.vku.bocuoi.yu.repository.MenuRepository;
import org.springframework.util.ObjectUtils;

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
        if (!ObjectUtils.isEmpty(menu.getMenuId())) {
            menuDto.setMenuId(menu.getMenuId());
        }
//        menuDto.setMenuId(menu.getMenu().getId());
        menuDto.setPath(menu.getPath());
        menuDto.setName(menu.getName());
        menuDto.setDescription(menu.getDescription());
        menuDto.setStatus(menu.getStatus());
        return menuDto;
    }
    public Menu toEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setMenuId(menuDto.getMenuId());
//        menu.setMenu(menuRepository.findById(menuDto.getMenuId()).orElse(null));
        menu.setPath(menuDto.getPath());
        menu.setName(menuDto.getName());
        menu.setDescription(menuDto.getDescription());
        menu.setStatus(menuDto.getStatus());
        return menu;
    }
}
