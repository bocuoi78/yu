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
public class DocumentaryDto implements Serializable {
    private Long id;
    private String number;
    private Date date;
    private String place;
    private String author;
    private String content;
    private String fileName;
    private String description;
    private Set<DocumentProcessingDto> documentProcessingDtoSet;
}
