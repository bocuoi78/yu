package com.vku.bocuoi.yu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "MENU")
@Data
public class Menu {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "MENU_ID")
//    private Menu menu;
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "PATH", length = 100, nullable = false)
    private String path;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "STATUS", nullable = false)
    private Boolean status = true;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "MENU_ROLE",
            joinColumns = @JoinColumn(name = "MENU_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roleList;
}
