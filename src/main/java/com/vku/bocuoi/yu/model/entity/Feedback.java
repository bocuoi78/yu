package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FEEDBACK", indexes = {
        @Index(name = "STUDENT_ID", columnList = "STUDENT_ID")
})
@Getter
@Setter
public class Feedback extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "CONTENT", length = 500, nullable = false)
    private String content;
}
