package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
public class Student extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "GENDER")
    private Boolean gender;

    @Column(name = "NATION", length = 100)
    private String nation;

    @Column(name = "CID", nullable = false, length = 13)
    private String cId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CID_DATE")
    private Date cIdDate;

    @Column(name = "CID_PLACE", length = 100)
    private String cIdPlace;

    @Column(name = "PHONE", nullable = false, length = 10)
    private String phone;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "RELIGION", length = 100)
    private String religion;

    @Column(name = "EDUCATIONAL_LEVEL", length = 100)
    private String educationalLevel;

    @Column(name = "QUALIFICATION", length = 100)
    private String qualification;

    @Column(name = "POLITICAL_THEORY", length = 100)
    private String politicalTheory;

    @Column(name = "ADDRESS_PERMANENT", length = 500)
    private String addressPermanent;

    @Column(name = "ADDRESS_TEMPORARY", length = 500)
    private String addressTemporary;

    @Column(name = "DATE_IN")
    private Date dateIn;

    @Column(name = "PLACE_IN", length = 100)
    private String placeIn;

    @Column(name = "IMAGE", length = 256)
    private String image;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password = "Abc@123";

    @OneToMany(mappedBy = "student")
    private Set<StudentUnit> studentUnitSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Volunteer> volunteerSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Feedback> feedbackSet = new LinkedHashSet<>();
}
