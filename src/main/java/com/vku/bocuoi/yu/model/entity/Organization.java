package com.vku.bocuoi.yu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORGANIZATION")
//@SQLDelete(sql = "UPDATE ORGANIZATION SET IS_DELETED=TRUE WHERE id=?")
//@Where(clause = "IS_DELETED=FALSE")
@Getter
@Setter
public class Organization extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "ORGANIZATION_LEVEL_ID", nullable = false)
    private Long organizationLevelId;

    @OneToMany(mappedBy = "organizationId")
    private Set<Student> studentSet = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "organizationList")
//    @JsonBackReference
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "DOCUMENT_ORGANIZATION",
//        joinColumns = @JoinColumn(name = "ORGANIZATION_ID"),
//        inverseJoinColumns = @JoinColumn(name = "DOCUMENT_ID"))
    private List<Document> documentList = new ArrayList<>();
}
