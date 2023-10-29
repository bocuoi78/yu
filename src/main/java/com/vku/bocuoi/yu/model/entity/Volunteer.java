package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VOLUNTEER", indexes = {
        @Index(name = "ACTIVITY_ID", columnList = "ACTIVITY_ID"),
        @Index(name = "STUDENT_ID", columnList = "STUDENT_ID")
})
@Getter
@Setter
public class Volunteer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTIVITY_ID", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;
}
