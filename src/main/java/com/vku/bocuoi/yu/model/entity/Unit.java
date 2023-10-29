package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "UNIT")
@SQLDelete(sql = "UPDATE UNIT SET IS_DELETED=TRUE WHERE id=?")
@Where(clause = "IS_DELETED=FALSE")
@Getter
@Setter
public class Unit extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "UNIT_ID")
    private Long unitId;

    @OneToMany(mappedBy = "unit")
    private Set<StudentUnit> studentUnitSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "unit")
    private Set<Activity> activitySet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "unit")
    private Set<DocumentProcessing> documentProcessingSet = new LinkedHashSet<>();
}
