package com.loong.tt.biztool.example.algorithm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Albert on 2020/6/24.
 */
public class CodeGenerator {

    private static final int INVITE_CODE_LENGTH = 8;

    private static final char STUFFS[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            '1', '2', '3', '4', '5', '6', '7', '8'};

    public static String random() {
        int count = 0;
        StringBuffer code = new StringBuffer();
        ThreadLocalRandom r = ThreadLocalRandom.current();
        while (count < INVITE_CODE_LENGTH) {
            code.append(STUFFS[r.nextInt(STUFFS.length)]);
            count++;
        }
        return code.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(random());
        }
    }
}
