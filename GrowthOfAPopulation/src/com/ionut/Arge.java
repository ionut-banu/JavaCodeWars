package com.ionut;

/**
 * Created by Ionut on Feb, 2020
 */
class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        int p1 = p0;
        int c = 0;
        while(p1 < p){
            p1 = (int) (p1 + (p1*(percent/100)) + aug);
            c++;
        }
        return c;
    }
}
