import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        int n = 53132221;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        String out = "";
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime(i) && !isPrime(n)) {
                while (n % i == 0) {
                    n = n / i;
                    addToMap(map, i);
                }
            }
        }
        if (isPrime(n)) {
            addToMap(map,n);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            out += "(" + key + (value > 1 ? "**" + value : "") + ")";
        }
        System.out.println(out);
    }

    static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt((double) n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void addToMap(Map map, int key) {
        int val = 1;
        if (map.containsKey(key)) {
            val += (int) map.get(key);
        }

        map.put(key, val);
    }
}
