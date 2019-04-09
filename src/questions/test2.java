package questions;

import java.util.HashMap;
import java.util.Scanner;

public class test2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] paths = s.split("");
        int num = Integer.parseInt(paths[0]);
        HashMap<String, Integer> map = new HashMap<>();
        System.out.print(digit(paths[1], map));
        for (int i = 2; i <= num; i ++) {
            System.out.println(" ");
            System.out.println(digit(paths[i], map));
        }

    }

    private static String digit(String path, HashMap<String, Integer> map) {
        if (path.charAt(path.length() - 1) == '/') path = path.substring(0, path.length() - 1);
        int value = 1;
        if (map.containsKey(path)) {
            value = map.get(path);
            value++;
        }
        map.put(path, value);
        String[] parts = path.split("/");
        if (parts.length == 2) return "1";
        if (parts.length == 3) return "11";
        StringBuilder result = new StringBuilder("1");
        for (int i = 2; i < parts.length; i ++) {
            result.append(value);
        }
        return result.toString();
    }
}
