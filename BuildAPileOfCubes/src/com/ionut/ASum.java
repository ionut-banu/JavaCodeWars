package com.ionut;

import java.math.BigInteger;

/**
 * Created by Ionut on Feb, 2020
 */
public class ASum {

    public static long findNb(long m) {
        BigInteger s = BigInteger.valueOf(0);
        int i = 1;
        while(s.compareTo(BigInteger.valueOf(m)) < 0){
            s = s.add(BigInteger.valueOf((long) Math.pow(i, 3)));
            if(s.equals(BigInteger.valueOf(m))){
                return i;
            }
            i++;
        }
        return -1;
    }
}
