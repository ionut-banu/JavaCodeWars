import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        int[] l = {295, 13, 463, 412, -86, -28, 20, -85, 272, -31, 420, 70, -26, 492, 337, 18, 370, 426, 57, 366, 33, 338, 53};
        Arrays.sort(l);
        System.out.println(Arrays.toString(l));
        String out = "";
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int max = Math.abs(l[0]) > Math.abs(l[l.length - 1]) ?  Math.abs(l[0]) : Math.abs(l[l.length - 1]);

        for (int i = 0; i <= max; i++) {
            if (Math.abs(i) > 1 && isPrime(i)) {
                for (int j = 0; j < l.length; j++) {
                    if (l[j] % i == 0) {
                        addToMap(map, i, l[j]);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            out += "(" + key + " " + value + ")";
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

    static void addToMap(Map map, int key, int amount) {

        if (map.containsKey(key)) {
            amount += (int) map.get(key);
        }
        System.out.println("adding " + key + "--" + amount);
        if(key<amount) {
            map.put(key, amount);
        }
    }
}
