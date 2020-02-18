package com.ionut;

import java.util.ArrayList;
import java.util.List;

public class ProperFractions {
    public static long properFractions(long n) {
        System.out.println(n);
        if (n == 1) return 0;
        int count = 0;
        List<Long> divList = getDiv(n);
        for (long i = 1; i < n; i++) {
            boolean prop = true;
            for (int j = 0; j < divList.size(); j++) {
                long div = divList.get(j);
                if (i >= div && i % div == 0) {
                    prop = false;
                    break;
                }
            }
            if (prop) count++;
        }
        return count;
    }

    private static List<Long> getDiv(long n) {
        List<Long> list = new ArrayList<>();
        for (long i = 2; i <= n / 2; i++) {
            if (n % i == 0) list.add(i);
        }
        return list;
    }
}