package questions;

import java.util.HashMap;

public class GetRandomString {

    public static class RandomPool {

        HashMap<String, Integer> map1;
        HashMap<Integer, String> map2;
        int size;

        public RandomPool() {
            map1 = new HashMap<>();
            map2 = new HashMap<>();
            size = 0;
        }

        public void add(String string) {
            map1.put(string, size);
            map2.put(size++, string);
        }

        public String get() {
            if (size == 0) return null;
            int index = (int) (Math.random() * size);
            return map2.get(index);
        }

        public String delete() {
            if (size == 0) return null;
            int index = (int) (Math.random() * size);
            String result = map2.get(index);
            map1.replace(result, size - 1);
            map2.replace(index, map2.get(size - 1));
            map1.remove(map2.get(size - 1));
            map2.remove(size - 1);
            return result;
        }
    }
}
