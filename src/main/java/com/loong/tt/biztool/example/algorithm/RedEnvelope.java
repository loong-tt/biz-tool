package com.loong.tt.biztool.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Albert on 2020/6/24.
 */
public class RedEnvelope {

    /**
     * 2倍均值法
     *
     * @param amount
     * @param num
     * @return
     */
    public List<Integer> get1(Integer amount, Integer num) {
        if (amount == null || num == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = num; i >= 2; i--) {
            int x = (amount << 1) / i;
            int obtain = random.nextInt(1, x);
            result.add(obtain);
            amount -= obtain;
        }
        result.add(amount);
        return result;
    }

    /**
     * 线段分割法
     *
     * @param amount
     * @param num
     * @return
     */
    public List<Integer> get2(Integer amount, Integer num) {

        List<Integer> list = new ArrayList<>();
        if (amount <= 0 || num <= 0) {
            return list;
        }
        Set<Integer> set = new HashSet<>();
        while (set.size() < num - 1) {
            int random = ThreadLocalRandom.current().nextInt(1, amount);
            set.add(random);
        }

        Integer[] amounts = set.toArray(new Integer[0]);
        Arrays.sort(amounts);
        list.add(amounts[0]);
        for (int i = 1; i < amounts.length; i++) {
            list.add(amounts[i] - amounts[i - 1]);
        }
        list.add(amount - amounts[amounts.length - 1]);
        return list;
    }
}
