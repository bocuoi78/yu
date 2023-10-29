package com.vku.bocuoi.yu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentProcessingDto implements Serializable {
    private Long documentId;
    private Long unitId;
    private Date issuedDate;
    private String description;
}
