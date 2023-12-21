package com.vku.bocuoi.yu.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {
    private StudentDto profile;
    private List<MenuDto> nav;
}
