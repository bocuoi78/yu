package com.vku.bocuoi.yu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@SQLDelete(sql = "UPDATE ROLE SET IS_DELETED=TRUE WHERE id=?")
@Where(clause = "IS_DELETED=FALSE")
@Getter
@Setter
public class Role{
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS", nullable = false)
    private Boolean status = true;

//    @JsonBackReference
    @ManyToMany(mappedBy = "roleSet")
//    @JoinTable(name = "STUDENT_UNIT_ROLE",
//            joinColumns = @JoinColumn(name = "ROLE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private Set<Student> studentSet = new LinkedHashSet<>();
}
