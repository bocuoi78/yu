package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "COMMENT", indexes = {
        @Index(name = "ACTIVITY_ID", columnList = "ACTIVITY_ID"),
        @Index(name = "COMMENT_ID", columnList = "COMMENT_ID")
})
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTIVITY_ID", nullable = false)
    private Activity activity;

    @Column(name = "TITLE", length = 100)
    private String title;

    @Column(name = "CONTENT", length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;
}
