package com.vku.bocuoi.yu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "DOCUMENTARY")
@Getter
@Setter
public class Documentary extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER", length = 100)
    private String number;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "PLACE", length = 100)
    private String place;

    @Column(name = "AUTHOR", length = 100)
    private String author;

    @Column(name = "CONTENT", length = 500)
    private String content;

    @Column(name = "FILE_NAME", length = 256)
    private String fileName;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @OneToMany(mappedBy = "document")
    private Set<DocumentProcessing> documentProcessingSet = new LinkedHashSet<>();
}
