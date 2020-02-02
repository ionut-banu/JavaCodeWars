package com.ionut;

/**
 * Created by Ionut on Feb, 2020
 */
public class Kata {
    public static String expandedForm(int num) {
        String s = "";
        int i = 0;
        while (num > 0 ){
            int c = num % 10;
            if (c != 0) s = appendZero(c, i) + (s.length() > 0 ? " + " : "")+ s;
            i++;
            num /= 10;
        }
        return s;
    }

    private static String appendZero(int n, int nr){
        StringBuilder sb = new StringBuilder(Integer.toString(n));
        for(int i = 0; i < nr; i++){
            sb.append('0');
        }
        return sb.toString();
    }
}
