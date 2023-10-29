package com.vku.bocuoi.yu.model.dto;

import com.vku.bocuoi.yu.model.entity.StudentUnit;
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
    private Date birthday;
    private Boolean gender;
    private String nation;
    private String cId;
    private Date cIdDate;
    private String cIdPlace;
    private String phone;
    private String email;
    private String religion;
    private String educationalLevel;
    private String qualification;
    private String politicalTheory;
    private String addressPermanent;
    private String addressTemporary;
    private Date dateIn;
    private String placeIn;
    private String image;
    private String password;
    private Set<StudentUnitDto> studentUnitDtoSet;
}
