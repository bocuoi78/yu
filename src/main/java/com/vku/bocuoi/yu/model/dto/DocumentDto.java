package com.vku.bocuoi.yu.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DocumentDto implements Serializable {
    private Long id;
    private String notation;
    private Long documentTypeId;
    private String name;
    private Date dateIssued;
    private Long organizationIssuedId;
    private String fileName;
    private String fileExtension;
    private String filePath;
    private String description;
}
