package com.loong.tt.biztool.example.filehandle;

import com.loong.tt.biztool.example.filehandle.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.util.List;

/**
 * Created by Albert on 2020/7/21.
 */
public class ReadExcel {

    public static void main(String[] args) {
        String path = "/Users/albert/Downloads/pintuan.csv";
        File file = new File(path);
        List<Row> rows = ExcelUtil.readExcel(file);
        System.out.println(rows.toString());
    }
}
