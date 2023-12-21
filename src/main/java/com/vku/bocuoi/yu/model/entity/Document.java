package com.vku.bocuoi.yu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "DOCUMENT")
@Getter
@Setter
public class Document extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTATION", nullable = false, length = 100)
    private String notation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DOCUMENT_TYPE", nullable = false)
    private DocumentType type;

    @Column(name = "DOCUMENT_NAME")
    private String name;

    @Column(name = "DATE_ISSUED")
    private Date dateIssued;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORGANIZATION_ISSUED")
    private Organization organizationIssued;

    @Column(name = "FILE_NAME", length = 256)
    private String fileName;

    @Column(name = "FILE_EXTENSION")
    private String fileExtension;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DOCUMENT_ORGANIZATION",
            joinColumns = @JoinColumn(name = "DOCUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORGANIZATION_ID"))
    private List<Organization> organizationList = new ArrayList<>();
}
