package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "DOCUMENT_PROCESSING")
@Getter
@Setter
public class DocumentProcessing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DOCUMENT_ID", nullable = false)
    private Documentary document;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UNIT_ID", nullable = false)
    private Unit unit;

    @Column(name = "ISSUED_DATE")
    private Date issuedDate;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;
}
