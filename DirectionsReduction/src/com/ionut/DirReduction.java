package com.ionut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ionut on Feb, 2020
 */

public class DirReduction {
    private final static String N = "NORTH";
    private final static String E = "EAST";
    private final static String S = "SOUTH";
    private final static String W = "WEST";

    public static String[] dirReduc(String[] arr) {
        List<String> in =  new LinkedList<String>(Arrays.asList(arr));
        boolean cleared;
        do {
            cleared = false;
            for(int i = 0; i < in.size() - 1; i++){
                if(!check(in.get(i), in.get(i + 1))){
                    in.remove(i);
                    in.remove(i);
                    cleared = true;
                }
            }
        } while(cleared);
        return in.toArray(new String[in.size()]);
    }

    private static boolean check(String a, String b){
        return ((a.equals(N) && !b.equals(S)) || ((a.equals(S) && !b.equals(N))))
                || ((a.equals(E) && !b.equals(W)) || ((a.equals(W) && !b.equals(E))));
    }
}
