package questions.grid;

import java.util.ArrayList;
import java.util.Scanner;

public class MaToProg {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        ArrayList<char[]> matrix = new ArrayList<>();
//        ArrayList<Character> column = new ArrayList<>();
//        char[] columnInput= null;
//        while (scanner.hasNextLine()) {
//            columnInput = scanner.nextLine().replace(" ", "").toCharArray();
//            matrix.add(columnInput);
//        }

        solve(get());
    }

    public static ArrayList<char[]> get() {
        ArrayList<char[]> list = new ArrayList<>();
        list.add(new char[] {'1', '2'});
        list.add(new char[] {'2', '1'});
        list.add(new char[] {'1', '2'});
        list.add(new char[] {'0', '1'});
        list.add(new char[] {'0', '1'});
        list.add(new char[] {'1', '1'});
        return list;
    }

    private static void solve(ArrayList<char[]> matrix) {
        int count = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j ++) {
                if (matrix.get(i)[j] == '2' && infect(matrix, i, j)) {
                    count ++;
                }
            }
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).length; j ++) {
                if (matrix.get(i)[j] == '1') {
                    System.out.println("-1");
                    return;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean infect(ArrayList<char[]> matrix, int i, int j) {
        char[] temp;
        boolean changed = false;
        if (i - 1 >= 0 && matrix.get(i - 1)[j] == '1') {
            for (int count = i - 1; count >= 0 && matrix.get(count)[j] == '1'; count --) {
                temp = matrix.get(count - 1);
                temp[j] = '2';
                matrix.set(count - 1, temp);
            }
            changed = true;
        }
        if (j + 1 < matrix.get(i).length && matrix.get(i)[j + 1] == '1') {
            for (int count = j + 1; count < matrix.get(i).length && matrix.get(i)[count] == '1'; count ++) {
                temp = matrix.get(i);
                temp[count] = '2';
                matrix.set(i, temp);
            }
            changed = true;
        }
        if (i + 1 < matrix.size() && matrix.get(i + 1)[j] == '1') {
            for (int count = i + 1; count < matrix.size() && matrix.get(count)[j] == '1'; count ++) {
                temp = matrix.get(count);
                temp[j] = '2';
                matrix.set(i + 1, temp);
            }
            changed = true;
        }
        if (j - 1 >= 0 && matrix.get(i)[j - 1] == '1') {
            for (int count = j - 1; count >= 0 && matrix.get(i)[count] == '1'; count --) {
                temp = matrix.get(i);
                temp[count] = '2';
                matrix.set(i, temp);
            }
            changed = true;
        }
        return changed;
    }
}
