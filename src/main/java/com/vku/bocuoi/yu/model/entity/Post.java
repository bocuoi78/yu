package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "POST", indexes = {
        @Index(name = "MENU_ID", columnList = "MENU_ID")
})
@Setter
@Getter
public class Post extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;

    @Column(name = "TITLE", length = 100)
    private String title;

    @Column(name = "CONTENT", length = 500)
    private String content;
}
