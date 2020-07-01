package com.loong.tt.biztool.example.stringhandle;

import org.springframework.util.StringUtils;

import java.util.function.Predicate;

/**
 * Created by Albert on 2020/6/24.
 */
public class MaskUtil {

    private static final String DEFAULT_MASK_CHAR = "*";

    private static final int DEFAULT_MASK_LENGTH = 3;

    public static String codeMask(String target, String mask) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        return mask(target, mask, i -> i == 0 || i == target.length() - 1);
    }

    public static String codeMask(String target) {
        return codeMask(target, DEFAULT_MASK_CHAR);
    }

    public static String phoneMask(String target, String mask) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        return mask(target, mask, i -> (i >= 0 && i < 3) || (i >= target.length() - 4 && i < target.length()));
    }

    public static String phoneMask(String target) {
        return phoneMask(target, DEFAULT_MASK_CHAR);
    }

    public static String emailMask(String target, String mask) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        int domainStart = target.lastIndexOf(".");
        return mask(target, mask, i -> (i >= 0 && i < 2) || (i >= domainStart && i < target.length()));
    }

    public static String emailMask(String target) {
        return emailMask(target, DEFAULT_MASK_CHAR);
    }

    public static String mask(String target, String mask, Predicate<Integer> predicate) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] seq = target.toCharArray();
        int cursor = 0;
        for (int i = 0; i < seq.length; i++) {
            if (predicate.test(i)) {
                stringBuilder.append(seq[i]);
                cursor = 0;
            } else {
                if (cursor <= DEFAULT_MASK_LENGTH) {
                    stringBuilder.append(mask);
                }
                cursor ++;
            }
        }
        return stringBuilder.toString();
    }
}
