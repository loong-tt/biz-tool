package com.loong.tt.biztool.example.filehandle;

import com.loong.tt.biztool.example.filehandle.util.CsvUtil;
import javafx.util.Pair;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 2020/7/21.
 */
public class ReadCsv {

    public static void main(String[] args) {
        String path = "/Users/albert/Downloads/pintuan.csv";
        File file = new File(path);
        Map<String, Pair<Integer, BigDecimal>> countMap = new HashMap<>();
        List<String[]> result = CsvUtil.readCsv(file);
        result.remove(0);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (String[] arr : result) {
            totalAmount = totalAmount.add(new BigDecimal(arr[2].trim()));
            String userId = arr[1];
            if (countMap.get(userId) == null) {
                countMap.put(userId, new Pair<>(1, new BigDecimal(arr[2].trim())));
            } else {
                Pair<Integer, BigDecimal> stored = countMap.get(userId);
                countMap.put(userId, new Pair<>(stored.getKey() + 1, stored.getValue().add(new BigDecimal(arr[2].trim()))));
            }
        }
        System.out.println(totalAmount.toPlainString());
        countMap.forEach((k, v) -> {
            if (v.getKey() > 4) {
                System.out.println(k + " | " + v.getKey() + " | " + v.getValue());
            }
        });
    }
}
