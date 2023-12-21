package com.vku.bocuoi.yu.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    private String id;
    private String name;
    private Boolean gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date birthday;
    private String cId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date cIdDate;
    private String cIdPlace;
    private String hometown;
    private String addressPermanent;
    private String nationality;
    private String nation;
    private String religion;
    private String educationalLevel;
    private String qualification;
    private String politicalTheory;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date dateIn;
    private String placeIn;
    private String email;
    private String phone;
    private String facebook;
//    private String image;
//    private String password;
//    private OrganizationDto organizationDto;
    private Long organizationId;
    private Set<RoleDto> roleDtoSet;
}
