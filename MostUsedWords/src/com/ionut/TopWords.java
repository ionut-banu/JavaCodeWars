package com.ionut;

import java.util.*;
import java.util.stream.Collectors;

public class TopWords {
    public static List<String> top3(String s) {
        s = s.replaceAll("[.,!?_:;/-]", " ");
        final Map<String, Integer> wordCounts = new HashMap<>();
        while (!s.isEmpty()) {
            s = s.trim();
            int next = s.indexOf('\n') > -1 ? Math.min(s.indexOf(' '), s.indexOf('\n')) : s.indexOf(' ');
            String t;
            if (next > 0) {
                t = s.substring(0, next);
                s = s.substring(next + 1).trim();
            } else {
                t = s;
                s = "";
            }
            //t = t.replaceAll("[\\W&&[^']]", "").replaceAll("[^A-Za-z' ]+|(?<=^|\\W)'|'(?=\\W|$)", " ").trim();
            int len = t.length();
            if (len > 0) {
                t = t.toLowerCase();
                char first = t.charAt(0);
                char last = t.charAt(len - 1);
                if (first == '\'') {
                    t = t.substring(1);
                    len--;
                    if (!t.isEmpty() && last == '\'') {
                        t = t.substring(0, len - 1);
                        len--;
                    }
                }

                if (!Character.isLetter(last) && last != '\'') {
                    t = t.substring(0, len - 1);
                }
                if (!t.isEmpty() && !t.equals("\'") ){
                    if (wordCounts.containsKey(t)) {
                        wordCounts.put(t, wordCounts.get(t) + 1);
                    } else {
                        wordCounts.put(t, 1);
                    }
                }

            }

        }

        if (wordCounts.size() == 0) {
            return Collections.emptyList();
        }
        Map<String, Integer> result = new LinkedHashMap<>();
        wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        List<String> list = result.keySet().stream().limit(3).collect(Collectors.toList());
        String[] arr = list.toArray(new String[list.size()]);

        return Arrays.asList(arr);
    }
}
