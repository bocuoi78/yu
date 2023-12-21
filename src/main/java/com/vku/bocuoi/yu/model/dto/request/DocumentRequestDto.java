package com.vku.bocuoi.yu.model.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentRequestDto {
    private String organizationId;
    private String keyword;
    private Long documentTypeId;
    private Date fromDate;
    private Date toDate;
    private Boolean statusIssued;
    private Long currentPage;
    private Long maxSize;
}
