package com.vku.bocuoi.yu.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocumentSendDto {
    private Long documentId;
    private Long organizationSendId;
    private List<Long> organizationReceiveIdList;
}
