package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STUDENT_UNIT", indexes = {
        @Index(name = "STUDENT_ID", columnList = "STUDENT_ID"),
        @Index(name = "UNIT_ID", columnList = "UNIT_ID")
})
@Getter
@Setter
public class StudentUnit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UNIT_ID", nullable = false)
    private Unit unit;

    @Column(name = "POSITION", nullable = false)
    private Integer position;

    @Column(name = "POSITION_NAME", nullable = false, length = 100)
    private String positionName;
}
