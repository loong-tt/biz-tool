/*
 * Copyright (c) 2020. Mek Global Limited.
 */

package com.loong.tt.biztool.example.filehandle.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert on 2020/7/17.
 */
@Slf4j
public class ExcelUtil {

    public static List<Row> readExcel(File file) {
        List<Row> rows = new ArrayList<>();
        String fileName = file.getName();
        try {
            if (fileName.endsWith(".xls")) {
                HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
                HSSFSheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        rows.add(row);
                    }
                }
            } else if (fileName.endsWith(".xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
                XSSFSheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        rows.add(row);
                    }
                }
            }
        } catch (IOException e) {
            log.warn("parse excel: {} failed", fileName);
        }
        return rows;
    }
}
