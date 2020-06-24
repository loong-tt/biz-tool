package com.loong.tt.biztool.example.proxy.model;

/**
 * Created by Albert on 2020/6/24.
 */
public class DemoImplA implements IDemoA {
    @Override
    public void doWhat(String str) {
        System.out.println(str);
    }
}
