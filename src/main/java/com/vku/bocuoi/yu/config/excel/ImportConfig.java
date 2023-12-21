package com.vku.bocuoi.yu.config.excel;

import com.vku.bocuoi.yu.model.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportConfig {
    private int sheetIndex;
    private int headerIndex;
    private int startRow;
    private Class dataClazz;
    private List<CellConfig> cellImportConfig;

    public static final ImportConfig studentImport;
    static {
        studentImport = new ImportConfig();
        studentImport.setSheetIndex(0);
        studentImport.setHeaderIndex(0);
        studentImport.setStartRow(1);
        studentImport.setDataClazz(StudentDto.class);
        List<CellConfig> studentImportCellConfig = new ArrayList<>();
        studentImportCellConfig.add(new CellConfig(1, "name"));
        studentImportCellConfig.add(new CellConfig(2, "id"));
        studentImportCellConfig.add(new CellConfig(3, "birthday"));
        studentImportCellConfig.add(new CellConfig(4, "cId"));
        studentImportCellConfig.add(new CellConfig(5, "email"));
        studentImportCellConfig.add(new CellConfig(6, "phone"));
        studentImportCellConfig.add(new CellConfig(7, "organizationId"));
        studentImport.setCellImportConfig(studentImportCellConfig);
    }
}
