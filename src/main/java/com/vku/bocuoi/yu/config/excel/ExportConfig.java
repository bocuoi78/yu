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
public class ExportConfig {
    private int sheetIndex;
    private int startRow;
    private Class dataClazz;
    private List<CellConfig> cellExportConfigList;

    public static final ExportConfig studentExport;
    static {
        studentExport = new ExportConfig();
        studentExport.setSheetIndex(0);
        studentExport.setStartRow(3);
        studentExport.setDataClazz(StudentDto.class);
        List<CellConfig> studentCellConfig = new ArrayList<>();
        studentCellConfig.add(new CellConfig(1, "name", "Họ và Tên"));
        studentCellConfig.add(new CellConfig(2, "id", "Mã sinh viên"));
        studentCellConfig.add(new CellConfig(3, "birthday", "Ngày sinh"));
        studentCellConfig.add(new CellConfig(4, "gender", "Giới tính"));
        studentCellConfig.add(new CellConfig(5, "nation", "Dân tộc"));
        studentCellConfig.add(new CellConfig(6, "religion", "Tôn giáo"));
        studentCellConfig.add(new CellConfig(7, "addressTemporary", "Quê quán"));
        studentCellConfig.add(new CellConfig(8, "addressPermanent", "Địa chỉ thường trú"));
        studentCellConfig.add(new CellConfig(9, "cId", "Số CCCD"));
        studentCellConfig.add(new CellConfig(10, "cIdDate", "Ngày cấp"));
        studentCellConfig.add(new CellConfig(11, "cIdPlace", "Nơi cấp"));
        studentCellConfig.add(new CellConfig(12, "educationalLevel", "Trình độ văn hóa"));
        studentCellConfig.add(new CellConfig(13, "qualification", "Trình độ chuyên môn"));
        studentCellConfig.add(new CellConfig(14, "politicalTheory", "Trình độ LLCT"));
        studentCellConfig.add(new CellConfig(15, "dateIn", "Ngày vào Đoàn"));
        studentCellConfig.add(new CellConfig(16, "placeIn", "Nơi vào Đoàn"));
        studentCellConfig.add(new CellConfig(17, "email", "Email"));
        studentCellConfig.add(new CellConfig(18, "phone", "Điện thoại liên hệ"));
        studentCellConfig.add(new CellConfig(19, "organization", "Chi đoàn"));

        studentExport.setCellExportConfigList(studentCellConfig);
    }
}
