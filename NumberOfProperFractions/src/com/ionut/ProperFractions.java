package com.ionut;

public class ProperFractions {
    public static long properFractions(long n) {
        System.out.println(n);
        if (n == 1) return 0;
        if (isPrime(n)) return n - 1;
        long product = n;
        if (isPrime(n)) return n - 1;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0) {
                do {
                    n = n / i;
                }while(n % i == 0);
                if (isPrime(i)) {
                    product = Math.round(product * (1 - 1.0 / i));
                }
            }
        }

        if (n != 1) {

            product = Math.round(product * (1 - 1.0 / n));
        }
        return product;

    }

    private static boolean isPrime(long n){
        for(int i = 2; i*i<=n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }

}