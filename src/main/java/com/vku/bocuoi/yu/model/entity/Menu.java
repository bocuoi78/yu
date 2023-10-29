package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MENU")
@Getter
@Setter
public class Menu {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;
}
