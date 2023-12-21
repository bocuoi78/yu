package com.vku.bocuoi.yu.config.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellConfig {
    private int columnIndex;
    private String fieldName;
    private String titleName;
    public CellConfig(int columnIndex, String fieldName) {
        this.columnIndex = columnIndex;
        this.fieldName = fieldName;
    }
}
