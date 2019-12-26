import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Pattern pAa = Pattern.compile("[A-Z][a-z]");
    static Pattern pA = Pattern.compile("[A-Z]");
    static Pattern pAad = Pattern.compile("[A-Z][a-z][1-9]");
    static Pattern pAd = Pattern.compile("[A-Z][1-9]");
    static Pattern pAadd = Pattern.compile("[A-Z][a-z][1-9][0-9]");
    static Pattern pAdd = Pattern.compile("[A-Z][1-9][0-9]");
    static Pattern p1 = Pattern.compile("\\((.*?)\\)");
    static Pattern p2 = Pattern.compile("\\[(.*?)\\]");
    static Pattern p3 = Pattern.compile("\\{(.*?)\\}");

    public static void main(String[] args) {
        String formula = "K4[ON(SO3)2]2";
        if(!isValid(formula)){
            throw new IllegalArgumentException();
        }
        HashMap hm = new HashMap<String, Integer>();

        int multiply = 1;
        Matcher m3 = p3.matcher(formula);
        while (m3.find()) {
            String s3 = m3.group(0);
            int c3 = getMultiply(s3, formula);
            int multiply3 = multiply * c3;
            s3 = match2(multiply3, formula, s3, hm);

            s3 = match1(multiply3, formula, s3, hm);
            processAtoms(hm, s3, multiply3);
            formula = formula.replace(m3.group(0) + (c3 != 1 ? c3 : ""), "");
        }

        formula = match2(multiply, formula, formula, hm);
        formula = match1(multiply, formula, formula, hm);

        processAtoms(hm, formula, 1);
    }

    static void processAtoms(HashMap hm, String s, int m) {
        s = findAtoms(hm, s, pAadd, 2, 2, m);
        s = findAtoms(hm, s, pAad, 1, 2, m);

        s = findAtoms(hm, s, pAdd, 2, 1, m);
        s = findAtoms(hm, s, pAd, 1, 1, m);

        s = findAtoms(hm, s, pAa, 0, 0, m);
        s = findAtoms(hm, s, pA, 0, 0, m);
    }

    static String findAtoms(HashMap hm, String s, Pattern p, int digits, int l, int mult) {
        Matcher m = p.matcher(s);
        while (m.find()) {
            String inside = m.group(0);
            int n = mult;
            if (digits != 0) {
                n *= Integer.parseInt(inside.substring(l));
                inside = inside.substring(0, inside.length() - digits);
            }
            addToMap(hm, inside, n);
            s = s.replace(m.group(0), "");
        }
        return s;
    }

    static void addToMap(HashMap hm, String key, int n) {
        int s = n;
        if (hm.containsKey(key)) {
            s += (int) hm.get(key);
        }

        hm.put(key, s);
    }

    static int getMultiply(String s, String parent) {
        int end = parent.indexOf(s) + s.length() + 2;
        String insideM = parent.substring(parent.indexOf(s) + s.length(), end < parent.length() ? end : parent.length());
        int begin = insideM.length() - 2 >= 0 ? insideM.length() - 2 : 0;
        try {
            return Integer.parseInt(insideM.substring(begin), insideM.length());
        } catch (NumberFormatException e1) {
            try {
                int e = insideM.length() > 1 ? insideM.length() - 1 : insideM.length();
                return Integer.parseInt(insideM.substring(begin, e));
            } catch (NumberFormatException e2) {
                return 1;
            }
        }
    }

    static String match1(int prevM, String formula, String s, HashMap hm) {
        Matcher m = p1.matcher(s);
        while (m.find()) {
            String s1 = m.group(0);
            int c1 = getMultiply(s1, formula);
            int multiply1 = prevM * c1;
            processAtoms(hm, s1, multiply1);
            s = s.replace(m.group(0) + (c1 != 1 ? c1 : ""), "");
        }
        return s;
    }

    static String match2(int prevM, String formula, String s, HashMap hm) {
        Matcher m2 = p2.matcher(s);
        while (m2.find()) {
            String s2 = m2.group();
            int c2 = getMultiply(s2, formula);
            int multiply2 = prevM * c2;
            s2 = match1(multiply2, formula, s2, hm);
            processAtoms(hm, s2, multiply2);
            s = s.replace(m2.group(0) + (c2 != 1 ? c2 : ""), "");
        }
        return s;
    }

    static boolean isValid(String s) {
        if(s.matches(".*[\\(].*[\\[].*[\\)].*")) return false;
        return s.matches(".*[A-Z].*") && getCount(s,'(') == getCount(s,')') && getCount(s,'[') == getCount(s,']') && getCount(s,'{') == getCount(s,'}');
    }

    static int getCount(String s, char c) {
        return (int) s.chars().filter(ch -> ch == c).count();
    }
}
