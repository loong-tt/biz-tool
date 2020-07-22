package com.loong.tt.biztool.example.filehandle.util;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Albert on 2020/7/21.
 */
public class CsvUtil {

    public static List<String[]> readCsv(File file) {
        List<String[]> result = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (reader != null) {
            result = reader.lines()
                    .filter(line -> !StringUtils.isEmpty(line))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
