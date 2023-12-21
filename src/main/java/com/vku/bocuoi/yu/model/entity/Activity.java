package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ACTIVITY", indexes = {
        @Index(name = "UNIT_ID", columnList = "UNIT_ID")
})
@Getter
@Setter
public class Activity extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "UNIT_ID")
//    private Organization organization;

    @Column(name = "FROM_TIME")
    private Date fromTime;

    @Column(name = "TO_TIME")
    private Date toTime;

    @Column(name = "LOCATION", length = 100)
    private String location;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "VOLUNTEER_MAX")
    private Integer volunteerMax;

    @OneToMany(mappedBy = "activity")
    private Set<Volunteer> volunteerSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "activity")
    private Set<Comment> commentSet = new LinkedHashSet<>();
}
