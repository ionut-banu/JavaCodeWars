import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ObservedPin {

    static int[][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};

    public static List<String> getPINs(String observed) {

        List<List<String>> neighbors = new ArrayList<>();
        List<String> out = new ArrayList<>();
        for (int i = 0; i < observed.length(); i++) {
            neighbors.add(getNeighbors(Character.getNumericValue(observed.charAt(i))));
        }

        test(0, "", neighbors, out);
        return out;
    } // getPINs

    static void test(int index, String temp, List<List<String>> neighbors, List<String> out) {
        if (temp.length() == neighbors.size()) {
            out.add(temp);
            return;
        }
        List<String> list = neighbors.get(index);
        for (int i = 0; i < list.size(); i++) {
            String c = temp + list.get(i);
            test(index + 1, c, neighbors, out);
        }
    }

    static List<String> getNeighbors(int c) {
        ArrayList<String> neighbors = new ArrayList<>();
        neighbors.add(String.valueOf(c));
        int row = 0;
        int col = 0;
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[0].length; j++) {
                if (keypad[i][j] == c) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row > 0 && keypad[row - 1][col] != -1) neighbors.add(String.valueOf(keypad[row - 1][col]));
        if (row < keypad.length - 1 && keypad[row + 1][col] != -1) neighbors.add(String.valueOf(keypad[row + 1][col]));
        if (col > 0 && keypad[row][col - 1] != -1) neighbors.add(String.valueOf(keypad[row][col - 1]));
        if (col < keypad[0].length - 1 && keypad[row][col + 1] != -1)
            neighbors.add(String.valueOf(keypad[row][col + 1]));
        neighbors.sort(Comparator.naturalOrder());
        return neighbors;
    }
} // ObservedPin
