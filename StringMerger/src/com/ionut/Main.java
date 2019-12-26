package com.ionut;

public class Main {

    public static void main(String[] args) {
        String s = "FF8\\62cH%0JtJfknW^?J\\f`h";
        String part1 = "F\\c%0JJfkW^\\f`";
        String part2 = "F862Htn?Jh";

        while (!s.isEmpty()) {
            String s1 = test(s, part1, part2);
            String s2 = test(s, part2, part1);

            if(s1.equals("") && s2.equals("")){
                System.out.println(false);
                return;
            }else if (s1.length() >= s2.length()) {
                part1 = part1.substring(s1.length());
                s = s.substring(s1.length());
            } else if (s1.length() < s2.length()) {
                part2 = part2.substring(s2.length());
                s = s.substring(s2.length());
            }
        }
        System.out.println(true);
        return;
    }

    static String test(String s1, String s2, String s3) {
        String out = "";
        for (int i = 0; i < s1.length(); i++) {
            if (i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
                out += s1.charAt(i);
            } else {
                while(out.length() > 1 && !s3.isEmpty()){
                    if(out.charAt(out.length()-1) == s3.charAt(0)){
                        out = out.substring(0,out.length()-1);
                        s3 = s3.substring(1);
                    } else {
                        return out;
                    }
                }
                return out;
            }
        }
        return out;
    }
}

